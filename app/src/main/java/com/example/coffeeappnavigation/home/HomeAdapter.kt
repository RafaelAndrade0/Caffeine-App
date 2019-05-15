package com.example.coffeeappnavigation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappnavigation.R
import com.example.coffeeappnavigation.commom.DateUtils
import com.example.coffeeappnavigation.model.Coffee
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class HomeAdapter : ListAdapter<Coffee, HomeAdapter.ViewHolder>(HomeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recyclerview_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coffee: Coffee) {
            var coffeeId = R.drawable.cafe_small
            when (coffee.coffeeSize) {
                0 -> coffeeId = R.drawable.cafe_small
                1 -> coffeeId = R.drawable.cafe_medium
                2 -> coffeeId = R.drawable.cafe_large
            }
            itemView.textViewCoffeeSize.text =
                itemView.context.getString(R.string.coffee_size_view, coffee.coffeeSize.toString())
            itemView.textViewCaffeine.text =
                itemView.context.getString(R.string.caffeine_view, coffee.caffeine.toString())
            itemView.textViewDate.text = DateUtils.toSimpleString(coffee.addedAt)
            Picasso.get()
                .load(coffeeId)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.placeholder_50)
                .into(itemView.imageViewThumb)
        }
    }
}