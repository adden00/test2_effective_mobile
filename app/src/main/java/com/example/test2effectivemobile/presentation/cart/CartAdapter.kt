package com.example.test2effectivemobile.presentation.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.CartItemBinding
import com.example.test2effectivemobile.domain.models.Basket

class CartAdapter: ListAdapter<Basket, CartAdapter.ItemHolder>(object : DiffUtil.ItemCallback<Basket>() {
    override fun areItemsTheSame(oldItem: Basket, newItem: Basket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Basket, newItem: Basket): Boolean {
        return oldItem == newItem
    }


}) {
    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun setData(item: Basket) {
            val binding = CartItemBinding.bind(view)
            binding.tvPhoneName.text = item.title
            binding.tvPrice.text = "\$${item.price}"
            Glide.with(binding.root.context).load(item.images).centerCrop().into(binding.imPhoneImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }
}