package com.sammy.sell_presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sammy.sell_domain.data.SearchCarResult
import com.sammy.sell_presentation.R
import com.sammy.sell_presentation.databinding.RowItemCarSearchBinding


class CarSearchAdapter :
    PagingDataAdapter<SearchCarResult, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as CarSearchViewHolder).bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: RowItemCarSearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_car_search,
            parent,
            false
        )
        return CarSearchViewHolder(binding)
    }

    inner class CarSearchViewHolder(private val binding: RowItemCarSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchCarResult) {

            binding.apply {
                searchCarImageView.setImageURI(item.imageUrl)
                searchTextView.text = item.title
                carPrice.text = item.marketplacePrice.toString()
            }
        }
    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<SearchCarResult>() {
            override fun areItemsTheSame(
                oldItem: SearchCarResult,
                newItem: SearchCarResult
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SearchCarResult,
                newItem: SearchCarResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}