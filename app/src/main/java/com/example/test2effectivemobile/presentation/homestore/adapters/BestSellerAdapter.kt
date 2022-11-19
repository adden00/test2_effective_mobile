package com.example.test2effectivemobile.presentation.homestore.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
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

class BestSellerAdapter(private val listener: Listener) :
    ListAdapter<BestSellerItem, BestSellerAdapter.ItemHolder>(object :
        DiffUtil.ItemCallback<BestSellerItem>() {
        override fun areItemsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BestSellerItem, newItem: BestSellerItem): Boolean {
            return oldItem == newItem
        }
    }) {

    private var curList = listOf<BestSellerItem>()

    class ItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun setData(item: BestSellerItem, listener: Listener) {
            val binding = BestSellerItemBinding.bind(view)
            val context = binding.root.context
            binding.tvPrice.text = "\$${item.price_without_discount}"
            binding.tvName.text = item.title
            binding.tvOldPrice.text = "\$${item.discount_price}"
            binding.tvOldPrice.paintFlags =
                binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            Glide.with(context).load(item.picture).into(binding.imItemPhoto)
            val likedIcon =
                if (item.is_favorites) R.drawable.ic_best_seller_like_enable_fg else R.drawable.ic_best_seller_like_disable_fg
            binding.imLikedFg.setImageDrawable(ContextCompat.getDrawable(context, likedIcon))
            itemView.setOnClickListener {
                listener.onItemClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.best_seller_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    fun setList(list: List<BestSellerItem>?) {
        curList = list ?: listOf()
        submitList(list)
    }

    interface Listener {
        fun onItemClick()
    }
}