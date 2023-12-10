package com.example.restaurant.model.dataClasses

import android.text.Editable
import androidx.room.PrimaryKey

data class DessertClass(

    val id : Int ,

    val name : String ,
    val price : String ,
    val description : String ,
    val imageUrl : String

)
