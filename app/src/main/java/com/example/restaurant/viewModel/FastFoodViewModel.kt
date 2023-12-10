package com.example.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Repository
import com.example.restaurant.model.dataClasses.FastFoodClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FastFoodViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    //Fast Food
    suspend fun getAllFastFood() : List<FastFoodClass>{
        return repository.getAllFastFood()
    }

    suspend fun insetFastFood(fastFoodClass: FastFoodClass){
        repository.insertFastFood(fastFoodClass)
    }

    suspend fun updateFastFood(fastFoodClass: FastFoodClass){
        repository.updateFastFood(fastFoodClass)
    }

    suspend fun deleteFastFood(id : Int){
        repository.deleteFastFood(id)
    }

}