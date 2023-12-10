package com.example.movies.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurant.model.dataClasses.OrderClass

@Database(entities = [OrderClass::class] , version = 1 , exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun doa() : Dao

}