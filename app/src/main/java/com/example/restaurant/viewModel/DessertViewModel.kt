package com.example.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Repository
import com.example.restaurant.model.dataClasses.DessertClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DessertViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //Dessert
    suspend fun getAllDessert() : List<DessertClass>{
        return repository.getAllDessert()
    }

    suspend fun insertDessert(dessertClass: DessertClass){
        repository.insertDessert(dessertClass)
    }

    suspend fun updateDessert(dessertClass: DessertClass){
        repository.updateDessert(dessertClass)
    }

    suspend fun deleteDessert(id: Int){
        repository.deleteDessert(id)
    }

}