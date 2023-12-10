package com.example.movies.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.restaurant.model.dataClasses.OrderClass

@Dao
interface Dao {


    @Query("SELECT * FROM OrderClass")
    fun getAllData() : List<OrderClass>

    @Insert
    fun insertAllDAta(moviesList: List<OrderClass>)

}