package com.example.coffeeappnavigation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.coffeeappnavigation.model.Coffee

class HomeDiffCallback : DiffUtil.ItemCallback<Coffee>() {
    override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
        return oldItem == newItem
    }

//    override fun getChangePayload(oldItem: Coffee, newItem: Coffee): Any? {
//        return super.getChangePayload(oldItem, newItem)
//    }
}