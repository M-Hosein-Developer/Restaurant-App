package com.example.restaurant.view.fastFood

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.restaurant.R
import com.example.restaurant.databinding.DialogAddNewItemFastFoodBinding
import com.example.restaurant.databinding.DialogDeleteItemBinding
import com.example.restaurant.databinding.FragmentFastFoodBinding
import com.example.restaurant.model.dataClasses.FastFoodClass
import com.example.restaurant.viewModel.FastFoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FastFoodFragment : Fragment(), FastFoodAdapter.ItemEvent {

    private lateinit var binding: FragmentFastFoodBinding
    private lateinit var adapter: FastFoodAdapter
    private val viewModel: FastFoodViewModel by viewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentFastFoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataToRecyclerView()
        btnAddFood()
        swipeRefresh()

    }

    private fun swipeRefresh() {

        binding.refreshSwipe.setOnRefreshListener {
            setDataToRecyclerView()

            lifecycleScope.launch {
                delay(1000)
                binding.refreshSwipe.isRefreshing = false
            }

        }
    }

    private fun setDataToRecyclerView() {

        lifecycleScope.launch {

            val data = viewModel.getAllFastFood()
            adapter = FastFoodAdapter(data, this@FastFoodFragment)
            binding.recyclerFastFood.adapter = adapter

        }

    }

    private fun btnAddFood() {

        binding.btnAddFastFood.setOnClickListener {

            val dialog = AlertDialog.Builder(requireActivity()).create()

            val dialogBinding = DialogAddNewItemFastFoodBinding.inflate(layoutInflater)

            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)

            dialog.show()

            dialogBinding.btnDone.setOnClickListener {

                if (dialogBinding.edtId.length() > 0 && dialogBinding.edtName.length() > 0 &&
                    dialogBinding.edtPrice.length() > 0 && dialogBinding.edtDesc.length() > 0 &&
                    dialogBinding.edtTime.length() > 0 && dialogBinding.edtKcal.length() > 0 &&
                    dialogBinding.edtImgUrl.length() > 0
                ) {

                    val txtId = dialogBinding.edtId.text.toString()
                    val txtName = dialogBinding.edtName.text.toString()
                    val txtPrice = dialogBinding.edtPrice.text.toString()
                    val txtDesc = dialogBinding.edtDesc.text.toString()
                    val txtTime = dialogBinding.edtTime.text.toString()
                    val txtKcal = dialogBinding.edtKcal.text.toString()
                    val txtImgUrl = dialogBinding.edtImgUrl.text.toString()


                    val newFastFood = FastFoodClass(
                        txtId.toInt(),
                        txtName,
                        txtPrice,
                        txtDesc,
                        txtTime,
                        txtKcal,
                        txtImgUrl
                    )

                    binding.recyclerFastFood.scrollToPosition(0)


                    lifecycleScope.launch {
                        viewModel.insetFastFood(newFastFood)
                    }


                    dialog.dismiss()

                } else {
                    Toast.makeText(requireActivity(), "Complete the fields", Toast.LENGTH_SHORT)
                        .show()
                }

                setDataToRecyclerView()

            }

        }


    }


    override fun onItemClick(fastFoodClass: FastFoodClass, position: Int) {

        val bundle = Bundle()
        bundle.putInt("id", fastFoodClass.id)
        bundle.putString("name", fastFoodClass.name)
        bundle.putString("price", fastFoodClass.price)
        bundle.putString("desc", fastFoodClass.description)
        bundle.putString("timeFood", fastFoodClass.time)
        bundle.putString("kcal", fastFoodClass.kcal)
        bundle.putString("imgUrl", fastFoodClass.imageUrl)

        findNavController().navigate(R.id.action_fastFoodFragment_to_detailFragment, bundle)



    }

    @SuppressLint("SetTextI18n")
    override fun onItemLongClick(fastFoodClass: FastFoodClass, position: Int) {

        val dialog = AlertDialog.Builder(requireActivity()).create()
        val dialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()


        dialogBinding.txtDelete.text = "آیا از حذف ${fastFoodClass.name} مطمئن هستید؟"

        dialogBinding.btnDoneDelete.setOnClickListener {

            lifecycleScope.launch {

                viewModel.deleteFastFood(fastFoodClass.id)
                setDataToRecyclerView()

            }

            dialog.dismiss()

        }

        dialogBinding.btnCancelDelete.setOnClickListener {
            dialog.dismiss()
        }


    }


}