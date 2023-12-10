package com.example.restaurant.view.dessertFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurant.databinding.DessertItemRecyclerBinding
import com.example.restaurant.model.dataClasses.DessertClass

class DessertAdapter(private val data : List<DessertClass>, private val itemEventDessert: ItemEventDessert) : RecyclerView.Adapter<DessertAdapter.DessertAdapterViewHolder>() {

    private lateinit var binding : DessertItemRecyclerBinding

    inner class DessertAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindView(dessertClass: DessertClass) {

            binding.txtNameDessert.text = dessertClass.name
            binding.txtPriceDessert.text  = dessertClass.price + "تومان"

            Glide.with(itemView)
                .load(dessertClass.imageUrl)
                .into(binding.imgDessert)

            itemView.setOnClickListener {
                itemEventDessert.onItemClick(dessertClass , adapterPosition)
            }

            itemView.setOnLongClickListener{
                itemEventDessert.onItemLongClick(dessertClass , adapterPosition)
                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertAdapterViewHolder {
        binding = DessertItemRecyclerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return DessertAdapterViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DessertAdapterViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    interface ItemEventDessert{
        fun onItemClick(dessertClass: DessertClass, position: Int)
        fun onItemLongClick(dessertClass: DessertClass, position: Int)
    }

}