package com.example.restaurant.view.foodFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurant.databinding.FoodItemRecyclerBinding
import com.example.restaurant.model.dataClasses.FoodClass

class FoodAdapter(private val data: List<FoodClass>, private val eventItem: ItemEventFood) : RecyclerView.Adapter<FoodAdapter.FoodAdapterHolder>(){

    private lateinit var binding: FoodItemRecyclerBinding

    inner class FoodAdapterHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(foodClass: FoodClass) {

            Glide.with(itemView)
                .load(foodClass.imageUrl)
                .into(binding.imgFood)

            binding.txtFoodName.text = foodClass.name
            binding.txtFoodPrice.text = foodClass.price + " " + "تومان"
            binding.txtTime.text = foodClass.time + " " + "دقیقه"

            itemView.setOnClickListener {
                eventItem.onItemClick(foodClass , adapterPosition)
            }

            itemView.setOnLongClickListener{
                eventItem.onItemLongClick(foodClass , adapterPosition)
                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapterHolder {
        binding = FoodItemRecyclerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return FoodAdapterHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FoodAdapterHolder, position: Int) {
        holder.bindView(data[position])
    }


        interface ItemEventFood{

        fun onItemClick(foodClass: FoodClass, position: Int)
        fun onItemLongClick(foodClass: FoodClass, position: Int)

    }

}