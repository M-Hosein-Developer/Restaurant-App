package com.example.restaurant.view.foodFrag

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
import com.bumptech.glide.RequestManager
import com.example.restaurant.R
import com.example.restaurant.databinding.DialogUpdateItemBinding
import com.example.restaurant.databinding.FragmentFoodDetailBinding
import com.example.restaurant.model.dataClasses.FoodClass
import com.example.restaurant.viewModel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailBinding
    private val viewModel : FoodViewModel by viewModels()
    @Inject lateinit var glide : RequestManager


    private lateinit var id : String
    private lateinit var name : String
    private lateinit var price : String
    private lateinit var desc : String
    private lateinit var time : String
    private lateinit var imgUrl : String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentFoodDetailBinding.inflate(layoutInflater , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        updateFastFood()

    }

    private fun updateFastFood() {

        binding.btnUpdat.setOnClickListener {

            val dialog = AlertDialog.Builder(requireActivity()).create()
            val dialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.edtNameUpdate.setText(name)
            dialogBinding.edtPriceUpdate.setText(price)
            dialogBinding.edtTimeUpdate.setText(time)

            dialogBinding.btnUpdate.setOnClickListener {

                if (dialogBinding.edtNameUpdate.length() > 0 &&
                    dialogBinding.edtPriceUpdate.length() > 0 &&
                    dialogBinding.edtTimeUpdate.length() > 0
                ) {

                    val txtName = dialogBinding.edtNameUpdate.text.toString()
                    val txtPrice = dialogBinding.edtPriceUpdate.text.toString()
                    val txtTime = dialogBinding.edtTimeUpdate.text.toString()


                    val newFood = FoodClass(
                        id.toInt(),
                        txtName,
                        txtPrice,
                        desc,
                        txtTime,
                        imgUrl
                    )


                    lifecycleScope.launch {
                        viewModel.updateFood(newFood)
                    }

                    findNavController().popBackStack(R.id.detailFragment , true)

//                    findNavController().navigate(R.id.action_detailFragment_to_fastFoodFragment)
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

        id = arguments?.getInt("id").toString()
        name = arguments?.getString("name").toString()
        price = arguments?.getString("price").toString()
        desc = arguments?.getString("desc").toString()
        time = arguments?.getString("timeFood").toString()
        imgUrl = arguments?.getString("imgUrl").toString()

        glide.load(imgUrl)
            .into(binding.imgCover)

        binding.txtIdDetail.text = id
        binding.txtNameDetail.text = name
        binding.txtPriceDetail.text = "$price تومان"
        binding.txtDescDetail.text = desc
        binding.txtTimeDetail.text = "$time دقیقه"

    }
}