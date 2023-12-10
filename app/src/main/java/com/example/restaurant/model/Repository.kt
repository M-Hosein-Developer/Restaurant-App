package com.example.restaurant.model

import com.example.restaurant.model.apiService.ApiService
import com.example.restaurant.model.dataClasses.DessertClass
import com.example.restaurant.model.dataClasses.FastFoodClass
import com.example.restaurant.model.dataClasses.FoodClass
import com.example.restaurant.model.dataClasses.OrderClass
import dessertToJsonObject
import fastFoodToJsonObject
import foodToJsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    //Order
    suspend fun getAllOrder() : List<OrderClass> {
        return apiService.getAllOrder()
    }

    fun deleteOrder(id : Int){
        CoroutineScope(Dispatchers.IO).launch{
            apiService.deleteOrder(id)
        }
    }

    //----------------------------------------------------------------------------------------------

    //Fast Food
    suspend fun getAllFastFood() : List<FastFoodClass>{
        return apiService.getAllFastFood()
    }

    suspend fun insertFastFood(fastFoodClass: FastFoodClass){
        apiService.insertFastFood(fastFoodToJsonObject(fastFoodClass))
    }

    suspend fun updateFastFood(fastFoodClass: FastFoodClass){
        apiService.updateFastFood(fastFoodClass.id , fastFoodToJsonObject(fastFoodClass))
    }

    suspend fun deleteFastFood(id : Int){
        apiService.deleteFastFood(id)
    }

    //----------------------------------------------------------------------------------------------

    //Dessert
    suspend fun getAllDessert() : List<DessertClass>{
        return apiService.getAllDessert()
    }

    suspend fun insertDessert(dessertClass: DessertClass){
        apiService.insertDessert(dessertToJsonObject(dessertClass))
    }

    suspend fun updateDessert(dessertClass: DessertClass){
        apiService.updateDessert(dessertClass.id , dessertToJsonObject(dessertClass))
    }

    suspend fun deleteDessert(id: Int){
        apiService.deleteDessert(id)
    }

    //----------------------------------------------------------------------------------------------

    //Food
    suspend fun getAllFood() : List<FoodClass>{
        return apiService.getAllFood()
    }

    suspend fun insertFood(foodClass: FoodClass){
        apiService.insertFood(foodToJsonObject(foodClass))
    }

    suspend fun updateFood(foodClass: FoodClass){
        apiService.updateFastFood(foodClass.id , foodToJsonObject(foodClass))
    }

    suspend fun deleteFood(id : Int){
        apiService.deleteFood(id)
    }
}