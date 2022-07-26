package com.sammy.sell_domain.repository


import androidx.paging.PagingData
import com.sammy.sell_domain.data.SearchCarResult
import kotlinx.coroutines.flow.Flow

interface SearchCarRepository {
    suspend fun searchCar(query: String, pageSize: Int): Flow<PagingData<SearchCarResult>>
}