package com.example.restaurant.view.drinkFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurant.databinding.DrinkItemRecyclerBinding
import com.example.restaurant.model.dataClasses.DrinkClass

class DrinkAdapter(private val data : List<DrinkClass>, private val itemEventDrink: ItemEventDrink ) : RecyclerView.Adapter<DrinkAdapter.DrinkAdapterViewHolder>() {

    private lateinit var binding : DrinkItemRecyclerBinding

    inner class DrinkAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(drinkClass: DrinkClass) {

            binding.txtNameDrink.text = drinkClass.name
            binding.txtPriceDrink.text  = drinkClass.price + "تومان"

            Glide.with(itemView)
                .load(drinkClass.imageUrl)
                .into(binding.imgDrink)

            itemView.setOnClickListener {
                itemEventDrink.onItemClick(drinkClass , adapterPosition)
            }

            itemView.setOnLongClickListener{
                itemEventDrink.onItemLongClick(drinkClass , adapterPosition)
                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkAdapterViewHolder {
        binding = DrinkItemRecyclerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return DrinkAdapterViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DrinkAdapterViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    interface ItemEventDrink{
        fun onItemClick(drinkClass: DrinkClass, position: Int)
        fun onItemLongClick(drinkClass: DrinkClass, position: Int)
    }

}