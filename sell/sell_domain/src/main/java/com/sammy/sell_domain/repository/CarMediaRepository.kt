package com.sammy.sell_domain.repository

import androidx.paging.PagingData
import com.sammy.sell_domain.data.CarMedia
import kotlinx.coroutines.flow.Flow

interface CarMediaRepository {
    suspend fun getCarMedia(carId: String, pageSize: Int): Flow<PagingData<CarMedia>>
}