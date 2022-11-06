package com.example.test2effectivemobile.presentation.homestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.BestSellerItemBinding
import com.example.test2effectivemobile.domain.models.BestSellerItem

class BestSellerAdapter: ListAdapter<BestSellerItem, BestSellerAdapter.ItemHolder>(object : DiffUtil.ItemCallback<BestSellerItem>() {
    override fun areItemsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
        return oldItem == newItem
    }

}) {
    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun setData(item: BestSellerItem) {
            val binding = BestSellerItemBinding.bind(view)
            val context = binding.root.context

            binding.tvPrice.text = item.price_without_discount.toString()
            binding.tvName.text = item.title
            binding.tvOldPrice.text = item.discount_price.toString()

            Glide.with(context).load(item.picture).into(binding.imItemPhoto)
            val likedIcon = if(item.is_favorites) R.drawable.ic_img_like_active else R.drawable.ic_img_like_disactive
            Glide.with(context).load(ContextCompat.getDrawable(context, likedIcon)).into(binding.imLiked)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.best_seller_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }
}