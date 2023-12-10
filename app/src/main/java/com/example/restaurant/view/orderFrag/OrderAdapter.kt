package com.example.restaurant.view.orderFrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.OrderItemRecycclerBinding
import com.example.restaurant.model.dataClasses.OrderClass

class OrderAdapter(private val data: List<OrderClass>?, private val eventItem: EventItem) :
    RecyclerView.Adapter<OrderAdapter.OrderAdapterViewHolder>() {

    lateinit var binding: OrderItemRecycclerBinding

    inner class OrderAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(orderClass: OrderClass) {


            binding.txtName.text = data!![adapterPosition].name
            binding.txtTable.text = data[adapterPosition].tableNumber.toString()

            itemView.setOnLongClickListener {

                eventItem.onLongClick(orderClass, adapterPosition)

                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapterViewHolder {
        binding =
            OrderItemRecycclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderAdapterViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: OrderAdapterViewHolder, position: Int) {
        holder.bindView(data!![position])
    }

    interface EventItem {
        fun onLongClick(orderClass: OrderClass, position: Int)
    }

}