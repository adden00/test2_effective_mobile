package com.example.test2effectivemobile.presentation.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.FragmentCartBinding
import com.google.android.material.snackbar.Snackbar

class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCartAdapter()
        observeCartItems()
        setButtons()
        observeSwipeToRefresh()

    }

    private fun setButtons() {
        binding.includeCartToolbar.btnBack.setOnClickListener {
            findNavController().popBackStack()

        }
        binding.btnCheckout.setOnClickListener {
            Snackbar.make(binding.root, "Making your order...", Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun observeSwipeToRefresh() {
        viewModel.isLoading.observe(requireActivity()) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadCartInfo()
        }
    }

    private fun initCartAdapter() {
        adapter = CartAdapter()
        binding.rcCart.adapter = adapter
        binding.rcCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("SetTextI18n")
    private fun observeCartItems() {
        viewModel.cartInfo.observe(requireActivity()) {

            adapter.submitList(it?.basket ?: listOf())
            binding.tvAllPrice.text = "\$${it?.total ?: 0} us"
            binding.tvDelivery.text = it?.delivery ?: "-"
        }

        viewModel.isLoading.observe(requireActivity()) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}