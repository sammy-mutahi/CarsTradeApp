package com.sammy.sell_domain.repository

import androidx.paging.PagingData
import com.sammy.sell_domain.data.PopularCar
import kotlinx.coroutines.flow.Flow

interface PopularCarRepository {
    suspend fun popularCars(isPopular: Boolean, pageSize: Int): Flow<PagingData<PopularCar>>
}