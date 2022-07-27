package com.sammy.sell_presentation.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PopularCarLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PopularCarLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PopularCarLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PopularCarLoadStateViewHolder {
        return PopularCarLoadStateViewHolder.create(parent, retry)
    }
}