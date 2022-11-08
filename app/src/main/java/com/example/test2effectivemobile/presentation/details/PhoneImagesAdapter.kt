package com.example.test2effectivemobile.presentation.details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.PhoneImageItemBinding

class PhoneImagesAdapter: ListAdapter<String, PhoneImagesAdapter.ItemHolder>(object : DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}) {
    class ItemHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun setData(url: String) {
            val binding = PhoneImageItemBinding.bind(view)
            Log.d("MyLog", url)
            Glide.with(binding.root.context).load(url).centerCrop().into(binding.imPhoneImage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.phone_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }
}