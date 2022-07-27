package com.sammy.sell_presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sammy.sell_domain.data.PopularCar
import com.sammy.sell_presentation.R
import com.sammy.sell_presentation.databinding.RowItemPopularBinding

class PopularCarsAdapter : PagingDataAdapter<PopularCar, RecyclerView.ViewHolder>(
    UIMODEL_COMPARATOR
) {

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<PopularCar>() {
            override fun areItemsTheSame(
                oldItem: PopularCar,
                newItem: PopularCar
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PopularCar,
                newItem: PopularCar
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as PopularCarsViewHolder).bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: RowItemPopularBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_popular,
            parent,
            false
        )
        return PopularCarsViewHolder(binding)
    }

    class PopularCarsViewHolder(private val binding: RowItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularCar) {
            Log.e("Adapter", "Name: ${item.name}")
            binding.apply {
                popularImageView.load(item.imageUrl) { placeholder(R.drawable.placeholder_background) }
                popularTextView.text = item.name
            }
        }
    }
}