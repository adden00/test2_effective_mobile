package com.example.test2effectivemobile.presentation.homestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.HotSalesItemBinding
import com.example.test2effectivemobile.domain.models.HotSalesItem

class HotSalesAdapter: ListAdapter<HotSalesItem, HotSalesAdapter.ItemHolder>(object : DiffUtil.ItemCallback<HotSalesItem>() {
    override fun areItemsTheSame(oldItem: HotSalesItem, newItem: HotSalesItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HotSalesItem, newItem: HotSalesItem): Boolean {
        return oldItem == newItem
    }
}) {
    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun setData(item: HotSalesItem) {
            val binding = HotSalesItemBinding.bind(view)

            Glide.with(binding.root.context).load(item.picture).centerCrop().into(binding.imBackground)
            binding.tvHotSalesDescription.text = item.subtitle
            binding.tvHotSalesName.text = item.title
            binding.iconNew.visibility = if (item.is_new) View.VISIBLE else View.GONE


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.hot_sales_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }
}