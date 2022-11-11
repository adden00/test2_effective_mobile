package com.example.test2effectivemobile.presentation.cart

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2effectivemobile.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCartAdapter()
        observeCartItems()
        setButtons()
        observeSwipeToRefresh()

    }

    private fun setButtons() {
        binding.includeCartToolbar.btnBack.setOnClickListener {
            finish()
        }
        binding.btnCheckout.setOnClickListener {
            Snackbar.make( binding.root, "Making your order...", Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun observeSwipeToRefresh() {
        viewModel.isLoading.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadCartInfo()
        }
    }


    private fun initCartAdapter() {
        adapter = CartAdapter()
        binding.rcCart.adapter = adapter
        binding.rcCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    @SuppressLint("SetTextI18n")
    private fun observeCartItems() {
        viewModel.cartInfo.observe(this){

            adapter.submitList(it?.basket ?: listOf())
            binding.tvAllPrice.text = "\$${it?.total ?: 0} us"
            binding.tvDelivery.text = it?.delivery ?: "-"


        }

        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
        }
    }




}