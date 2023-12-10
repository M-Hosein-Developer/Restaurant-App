package com.example.restaurant.view.drinkFrag

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
import com.example.restaurant.databinding.FragmentDrinksBinding
import com.example.restaurant.model.dataClasses.DrinkClass
import com.example.restaurant.viewModel.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DrinksFragment : Fragment() , DrinkAdapter.ItemEventDrink {

    private lateinit var binding: FragmentDrinksBinding
    private lateinit var adapter: DrinkAdapter
    private val viewModel: DrinkViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDrinksBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh()
        setDataToRecyclerView()
        btnAddDrink()

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

            val data = viewModel.getAllDrink()
            adapter = DrinkAdapter(data, this@DrinksFragment)
            binding.recyclerDrink.adapter = adapter

        }

    }
    private fun btnAddDrink() {

        binding.btnAddDrink.setOnClickListener {

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


                    val newDrink = DrinkClass(
                        txtId.toInt(), txtName, txtPrice, txtDesc, txtImgUrl
                    )

                    lifecycleScope.launch {
                        viewModel.insertDrink(newDrink)
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

    override fun onItemClick(drinkClass: DrinkClass, position: Int) {
        val bundle = Bundle()
        bundle.putInt("idDessert", drinkClass.id)
        bundle.putString("nameDessert", drinkClass.name)
        bundle.putString("priceDessert", drinkClass.price)
        bundle.putString("descDessert", drinkClass.description)
        bundle.putString("imgUrlDessert", drinkClass.imageUrl)

        findNavController().navigate(R.id.action_drinksFragment_to_detailDrinkFragment , bundle)

    }

    override fun onItemLongClick(drinkClass: DrinkClass, position: Int) {


    }

}