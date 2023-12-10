package com.example.restaurant.view.orderFrag

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.databinding.DialogDeleteItemBinding
import com.example.restaurant.databinding.FragmentOrderBinding
import com.example.restaurant.model.apiService.ApiService
import com.example.restaurant.model.dataClasses.OrderClass
import com.example.restaurant.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OrderFragment : Fragment(), OrderAdapter.EventItem {

    private lateinit var binding: FragmentOrderBinding


    @Inject
    lateinit var apiService: ApiService

    private val viewModel: OrderViewModel by viewModels()
    private lateinit var adapter: OrderAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setDataToRecyclerView()
        swipeRefresh()

    }

    private fun setDataToRecyclerView(){

        lifecycleScope.launch {

            val data = viewModel.getAllOrder()

            if (data.isNotEmpty()) {
                adapter = OrderAdapter(data, this@OrderFragment)
                binding.orderRecyclerView.adapter = adapter
                binding.orderRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

                binding.txtEmpty.visibility = View.GONE
            } else {

                binding.txtEmpty.visibility = View.VISIBLE

            }

        }

    }

    private fun swipeRefresh(){

        binding.refreshSwipe.setOnRefreshListener {
            setDataToRecyclerView()
            lifecycleScope.launch {
                delay(1000)
                binding.refreshSwipe.isRefreshing = false
            }

        }

    }

    @SuppressLint("SetTextI18n")
    override fun onLongClick(orderClass: OrderClass, position: Int) {


        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireActivity()).create()
        val dialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()


        dialogBinding.txtDelete.text = "آیا از حذف سفارش میز شماره  ${orderClass.tableNumber} مطمئن هستید؟"

        dialogBinding.btnDoneDelete.setOnClickListener {

            lifecycleScope.launch {

                viewModel.deleteOrder(orderClass.id)

            }

            setDataToRecyclerView()
            dialog.dismiss()

        }

        dialogBinding.btnCancelDelete.setOnClickListener {
            dialog.dismiss()
        }

    }


}