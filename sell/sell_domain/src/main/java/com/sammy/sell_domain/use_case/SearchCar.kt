package com.sammy.sell_domain.use_case

import androidx.paging.PagingData
import com.sammy.sell_domain.data.SearchCarResult
import com.sammy.sell_domain.repository.SearchCarRepository
import kotlinx.coroutines.flow.Flow

class SearchCar(
    private val repository: SearchCarRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<SearchCarResult>> {
        return repository.searchCar(query = query, pageSize = 24)
    }
}