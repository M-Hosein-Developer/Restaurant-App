package com.example.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Repository
import com.example.restaurant.model.dataClasses.DrinkClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //Drink
    suspend fun getAllDrink() : List<DrinkClass>{
        return repository.getAllDrink()
    }

    suspend fun insertDrink(drinkClass: DrinkClass){
        repository.insertDrink(drinkClass)
    }

    suspend fun updateDrink(drinkClass: DrinkClass){
        repository.updateDrink(drinkClass)
    }

    suspend fun deleteDrink(id: Int){
        repository.deleteDrink(id)
    }

}