package com.example.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Repository
import com.example.restaurant.model.dataClasses.FoodClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    //Food
    suspend fun getAllFood() : List<FoodClass>{
        return repository.getAllFood()
    }

    suspend fun insetFood(foodClass: FoodClass){
        repository.insertFood(foodClass)
    }

    suspend fun updateFood(foodClass: FoodClass){
        repository.updateFood(foodClass)
    }

    suspend fun deleteFood(id : Int){
        repository.deleteFood(id)
    }


}