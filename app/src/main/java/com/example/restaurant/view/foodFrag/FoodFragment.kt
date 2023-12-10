package com.example.restaurant.view.foodFrag

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
import com.example.restaurant.databinding.DialogAddNewItemFoodBinding
import com.example.restaurant.databinding.DialogDeleteItemBinding
import com.example.restaurant.databinding.FragmentFoodBinding
import com.example.restaurant.model.dataClasses.FoodClass
import com.example.restaurant.viewModel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodFragment : Fragment() , FoodAdapter.ItemEventFood {

    lateinit var binding : FragmentFoodBinding
    private lateinit var adapter: FoodAdapter
    private val viewModel: FoodViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFoodBinding.inflate(layoutInflater , container , false)
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

            val data = viewModel.getAllFood()
            adapter = FoodAdapter(data, this@FoodFragment)
            binding.recyclerFood.adapter = adapter

        }

    }

    private fun btnAddFood() {

        binding.btnAddFood.setOnClickListener {

            val dialog = AlertDialog.Builder(requireActivity()).create()

            val dialogBinding = DialogAddNewItemFoodBinding.inflate(layoutInflater)

            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)

            dialog.show()

            dialogBinding.btnDone.setOnClickListener {

                if (dialogBinding.edtId.length() > 0 && dialogBinding.edtName.length() > 0 &&
                    dialogBinding.edtPrice.length() > 0 && dialogBinding.edtDesc.length() > 0 &&
                    dialogBinding.edtTime.length() > 0 && dialogBinding.edtImgUrl.length() > 0
                ) {

                    val txtId = dialogBinding.edtId.text.toString()
                    val txtName = dialogBinding.edtName.text.toString()
                    val txtPrice = dialogBinding.edtPrice.text.toString()
                    val txtDesc = dialogBinding.edtDesc.text.toString()
                    val txtTime = dialogBinding.edtTime.text.toString()
                    val txtImgUrl = dialogBinding.edtImgUrl.text.toString()


                    val newFood = FoodClass(
                        txtId.toInt(),
                        txtName,
                        txtPrice,
                        txtDesc,
                        txtTime,
                        txtImgUrl
                    )

                    binding.recyclerFood.scrollToPosition(0)


                    lifecycleScope.launch {
                        viewModel.insetFood(newFood)
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

    override fun onItemClick(foodClass: FoodClass, position: Int) {

        val bundle = Bundle()
        bundle.putInt("id", foodClass.id)
        bundle.putString("name", foodClass.name)
        bundle.putString("price", foodClass.price)
        bundle.putString("desc", foodClass.description)
        bundle.putString("timeFood", foodClass.time)
        bundle.putString("imgUrl", foodClass.imageUrl)

        findNavController().navigate(R.id.action_foodFragment_to_foodDetailFragment, bundle)


    }

    override fun onItemLongClick(foodClass: FoodClass, position: Int){

        val dialog = AlertDialog.Builder(requireActivity()).create()
        val dialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()


        dialogBinding.txtDelete.text = "آیا از حذف ${foodClass.name} مطمئن هستید؟"

        dialogBinding.btnDoneDelete.setOnClickListener {

            lifecycleScope.launch {

                viewModel.deleteFood(foodClass.id)
                setDataToRecyclerView()

            }

            dialog.dismiss()

        }

        dialogBinding.btnCancelDelete.setOnClickListener {
            dialog.dismiss()
        }



    }


}