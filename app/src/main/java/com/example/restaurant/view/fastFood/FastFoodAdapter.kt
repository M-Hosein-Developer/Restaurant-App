package com.example.restaurant.view.fastFood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurant.databinding.FastfoodItemRecyclerBinding
import com.example.restaurant.model.dataClasses.FastFoodClass

class FastFoodAdapter(private val data: List<FastFoodClass>, private val eventItem: ItemEvent) : RecyclerView.Adapter<FastFoodAdapter.FastFoodAdapterHolder>(){

    private lateinit var binding: FastfoodItemRecyclerBinding

    inner class FastFoodAdapterHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(fastFoodClass: FastFoodClass) {

            Glide.with(itemView)
                .load(fastFoodClass.imageUrl)
                .into(binding.imgFastFood)

            binding.txtName.text = fastFoodClass.name
            binding.txtPrice.text = fastFoodClass.price + " " + "تومان"
            binding.txtTime.text = fastFoodClass.time + " " + "دقیقه"
            binding.txtKcal.text = fastFoodClass.kcal + " " + "کیلوکالری"

            itemView.setOnClickListener {
                eventItem.onItemClick(fastFoodClass , adapterPosition)
            }

            itemView.setOnLongClickListener{
                eventItem.onItemLongClick(fastFoodClass , adapterPosition)
                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastFoodAdapterHolder {
        binding = FastfoodItemRecyclerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return FastFoodAdapterHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FastFoodAdapterHolder, position: Int) {
        holder.bindView(data[position])
    }


        interface ItemEvent{

        fun onItemClick(fastFoodClass: FastFoodClass, position: Int)
        fun onItemLongClick(fastFoodClass: FastFoodClass, position: Int)

    }

}