package com.example.restaurant.model.dataClasses

import android.text.Editable
import androidx.room.PrimaryKey

data class FastFoodClass(


    val id: Int,

    val name: String,
    val price: String,
    val description: String,
    val time: String,
    val kcal: String,
    val imageUrl: String

)
