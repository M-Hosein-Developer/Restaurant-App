package com.example.restaurant.model.apiService

import com.example.restaurant.model.dataClasses.DessertClass
import com.example.restaurant.model.dataClasses.DrinkClass
import com.example.restaurant.model.dataClasses.FastFoodClass
import com.example.restaurant.model.dataClasses.FoodClass
import com.example.restaurant.model.dataClasses.OrderClass
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    //Order
    @GET("getAllOrder")
    suspend fun getAllOrder() : List<OrderClass>

    @DELETE("order/deleting{id}")
    suspend fun deleteOrder(@Path("id") id : Int)

    //----------------------------------------------------------------------------------------------

    //Fast Food
    @GET("getAllFastFood")
    suspend fun getAllFastFood() : List<FastFoodClass>

    @POST("insertFastFood")
    suspend fun insertFastFood(@Body body: JsonObject)

    @PUT("fastFood/update{id}")
    suspend fun updateFastFood(@Path("id") id : Int , @Body body: JsonObject)

    @DELETE("fastFood/deleting{id}")
    suspend fun deleteFastFood(@Path("id") id : Int)

    //----------------------------------------------------------------------------------------------

    //Dessert
    @GET("getAllDesserts")
    suspend fun getAllDessert() : List<DessertClass>

    @POST("insertDesserts")
    suspend fun insertDessert(@Body body: JsonObject)

    @PUT("desserts/update{id}")
    suspend fun updateDessert(@Path("id") id : Int , @Body body: JsonObject)

    @DELETE("desserts/deleting{id}")
    suspend fun deleteDessert(@Path("id") id : Int)

    //----------------------------------------------------------------------------------------------

    //Food
    @GET("getAllFood")
    suspend fun getAllFood() : List<FoodClass>

    @POST("insertFood")
    suspend fun insertFood(@Body body: JsonObject)

    @PUT("Food/update{id}")
    suspend fun updateFood(@Path("id") id : Int , @Body body: JsonObject)

    @DELETE("Food/deleting{id}")
    suspend fun deleteFood(@Path("id") id : Int)


    //Drink
    @GET("getDrinks")
    suspend fun getAllDrink() : List<DrinkClass>

    @POST("insertDrinks")
    suspend fun insertDrink(@Body body: JsonObject)

    @PUT("drinks/update{id}")
    suspend fun updateDrink(@Path("id") id : Int , @Body body: JsonObject)

    @DELETE("drinks/deleting{id}")
    suspend fun deleteDrink(@Path("id") id : Int)

}