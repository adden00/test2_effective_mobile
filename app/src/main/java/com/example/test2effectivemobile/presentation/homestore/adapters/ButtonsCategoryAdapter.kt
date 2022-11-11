package com.example.test2effectivemobile.presentation.homestore.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ButtonCategoryItemBinding
import com.example.test2effectivemobile.presentation.homestore.models.ButtonCategoryModel

class ButtonsCategoryAdapter(private val listener: Listener) :
    ListAdapter<ButtonCategoryModel, ButtonsCategoryAdapter.ItemHolder>(object :
        DiffUtil.ItemCallback<ButtonCategoryModel>() {
        override fun areItemsTheSame(
            oldItem: ButtonCategoryModel,
            newItem: ButtonCategoryModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ButtonCategoryModel,
            newItem: ButtonCategoryModel
        ): Boolean {
            return oldItem == newItem
        }

    }) {
    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ButtonCategoryItemBinding.bind(view)
        fun setData(item: ButtonCategoryModel, listener: Listener) {
            binding.btnCategory.background =
                ContextCompat.getDrawable(binding.root.context, item.backGround)
            binding.imCategory.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    item.icon
                )
            )
            binding.tvCategory.text = item.name
            binding.tvCategory.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    item.textColor
                )
            )
            binding.btnCategory.setOnClickListener {
                listener.onClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.button_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }


    interface Listener {
        fun onClick(id: Int)
    }
}