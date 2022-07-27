package com.sammy.sell_presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sammy.sell_presentation.R
import com.sammy.sell_presentation.databinding.ListItemLoadStateBinding

class PopularCarLoadStateViewHolder(
    private val binding: ListItemLoadStateBinding,
    retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMessageTextView.text = loadState.error.localizedMessage
        }
        binding.shimmerViewContainer.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMessageTextView.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        internal fun create(parent: ViewGroup, retry: () -> Unit): PopularCarLoadStateViewHolder {
            val binding: ListItemLoadStateBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_load_state,
                parent,
                false
            )
            return PopularCarLoadStateViewHolder(binding, retry)
        }
    }
}