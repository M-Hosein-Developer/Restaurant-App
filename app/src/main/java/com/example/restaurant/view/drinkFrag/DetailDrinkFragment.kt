package com.example.restaurant.view.drinkFrag

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
import com.bumptech.glide.RequestManager
import com.example.restaurant.databinding.DialogUpdateDessertItemBinding
import com.example.restaurant.databinding.FragmentDetailDessertBinding
import com.example.restaurant.model.dataClasses.DrinkClass
import com.example.restaurant.viewModel.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailDrinkFragment : Fragment() {

    private lateinit var binding : FragmentDetailDessertBinding
    private val viewModel : DrinkViewModel by viewModels()
    @Inject lateinit var glide : RequestManager

    private lateinit var id : String
    private lateinit var name : String
    private lateinit var price : String
    private lateinit var desc : String
    private lateinit var imgUrl : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailDessertBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        updateDesserts()

    }

    private fun updateDesserts() {

        binding.btnUpdateDessert.setOnClickListener {

            val dialog = AlertDialog.Builder(requireActivity()).create()
            val dialogBinding = DialogUpdateDessertItemBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.edtNameUpdateDessert.setText(name)
            dialogBinding.edtPriceUpdateDessert.setText(price)

            dialogBinding.btnUpdate.setOnClickListener {

                if (dialogBinding.edtNameUpdateDessert.length() > 0 &&
                    dialogBinding.edtPriceUpdateDessert.length() > 0
                ) {

                    val txtName = dialogBinding.edtNameUpdateDessert.text.toString()
                    val txtPrice = dialogBinding.edtPriceUpdateDessert.text.toString()


                    val newDrink = DrinkClass(
                        id.toInt(),
                        txtName,
                        txtPrice,
                        desc,
                        imgUrl
                    )



                    lifecycleScope.launch {
                        viewModel.updateDrink(newDrink)
                    }

                    dialog.dismiss()

                } else {
                    Toast.makeText(requireActivity(), "Complete the fields", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {

        id = arguments?.getInt("idDessert").toString()
        name = arguments?.getString("nameDessert").toString()
        price = arguments?.getString("priceDessert").toString()
        desc = arguments?.getString("descDessert").toString()
        imgUrl = arguments?.getString("imgUrlDessert").toString()

        glide.load(imgUrl)
            .into(binding.imgCoverDessert)

        binding.txtIdDetailDessert.text = id
        binding.txtNameDetailDessert.text = name
        binding.txtPriceDetailDessert.text = "$price تومان"
        binding.txtDescDetailDessert.text = desc

    }

}