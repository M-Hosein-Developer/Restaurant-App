package com.example.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.example.restaurant.model.Repository
import com.example.restaurant.model.dataClasses.OrderClass
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //Order
    suspend fun getAllOrder() : List<OrderClass> {
        return repository.getAllOrder()
    }

    fun deleteOrder(id : Int){
        repository.deleteOrder(id)
    }
}