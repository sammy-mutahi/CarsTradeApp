package com.sammy.sell_domain.use_case

import androidx.paging.PagingData
import com.sammy.sell_domain.data.PopularCar
import com.sammy.sell_domain.repository.PopularCarRepository
import kotlinx.coroutines.flow.Flow

class GetPopularCars(
    private val repository: PopularCarRepository
) {
    suspend operator fun invoke(): Flow<PagingData<PopularCar>> {
        return repository.popularCars(isPopular = true, pageSize = 20)
    }
}