package com.example.restaurant.view.dessertFragment

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
import com.example.restaurant.databinding.DialogAddNewItemDessertBinding
import com.example.restaurant.databinding.DialogDeleteItemBinding
import com.example.restaurant.databinding.FragmentDessertsBinding
import com.example.restaurant.model.dataClasses.DessertClass
import com.example.restaurant.viewModel.DessertViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DessertsFragment : Fragment(), DessertAdapter.ItemEventDessert {

    private lateinit var binding: FragmentDessertsBinding
    private lateinit var adapter: DessertAdapter
    private val viewModel: DessertViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDessertsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh()
        setDataToRecyclerView()
        btnAddDessert()

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

            val data = viewModel.getAllDessert()
            adapter = DessertAdapter(data, this@DessertsFragment)
            binding.recyclerDessert.adapter = adapter

        }

    }
    private fun btnAddDessert() {

        binding.btnAddDessert.setOnClickListener {

            val dialog = AlertDialog.Builder(requireActivity()).create()

            val dialogBinding = DialogAddNewItemDessertBinding.inflate(layoutInflater)

            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)

            dialog.show()

            dialogBinding.btnDoneDessert.setOnClickListener {

                if (dialogBinding.edtIdDessert.length() > 0 && dialogBinding.edtNameDessert.length() > 0 && dialogBinding.edtPriceDessert.length() > 0 && dialogBinding.edtDescDessert.length() > 0 && dialogBinding.edtImgUrlDessert.length() > 0) {

                    val txtId = dialogBinding.edtIdDessert.text.toString()
                    val txtName = dialogBinding.edtNameDessert.text.toString()
                    val txtPrice = dialogBinding.edtPriceDessert.text.toString()
                    val txtDesc = dialogBinding.edtDescDessert.text.toString()
                    val txtImgUrl = dialogBinding.edtImgUrlDessert.text.toString()


                    val newDessert = DessertClass(
                        txtId.toInt(), txtName, txtPrice, txtDesc, txtImgUrl
                    )

                    lifecycleScope.launch {
                        viewModel.insertDessert(newDessert)
                    }

                    dialog.dismiss()

                }else{
                    Toast.makeText(requireActivity(), "Complete the fields", Toast.LENGTH_SHORT)
                        .show()
                }
                setDataToRecyclerView()

            }

        }

    }


    override fun onItemClick(dessertClass: DessertClass, position: Int) {

        val bundle = Bundle()
        bundle.putInt("idDessert", dessertClass.id)
        bundle.putString("nameDessert", dessertClass.name)
        bundle.putString("priceDessert", dessertClass.price)
        bundle.putString("descDessert", dessertClass.description)
        bundle.putString("imgUrlDessert", dessertClass.imageUrl)

        findNavController().navigate(R.id.action_dessertsFragment_to_detailDessertFragment, bundle)


    }
    @SuppressLint("SetTextI18n")
    override fun onItemLongClick(dessertClass: DessertClass, position: Int) {

        val dialog = AlertDialog.Builder(requireActivity()).create()
        val dialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()


        dialogBinding.txtDelete.text = "آیا از حذف ${dessertClass.name} مطمئن هستید؟"

        dialogBinding.btnDoneDelete.setOnClickListener {

            lifecycleScope.launch {

                viewModel.deleteDessert(dessertClass.id)
                setDataToRecyclerView()

            }

            dialog.dismiss()

        }

        dialogBinding.btnCancelDelete.setOnClickListener {
            dialog.dismiss()
        }

    }


}