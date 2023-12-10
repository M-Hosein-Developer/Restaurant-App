package com.example.restaurant.model.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderClass(

    @PrimaryKey
    val id: Int ,

    val name : String,
    val tableNumber : Int

)
