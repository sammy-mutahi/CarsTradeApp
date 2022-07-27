package com.sammy.sell_domain.use_case

import androidx.paging.PagingData
import com.sammy.sell_domain.data.CarMedia
import com.sammy.sell_domain.repository.CarMediaRepository
import kotlinx.coroutines.flow.Flow

class GetCarMedia(
    private val repository: CarMediaRepository
) {
    suspend operator fun invoke(carId: String): Flow<PagingData<CarMedia>> {
        return repository.getCarMedia(carId = carId, pageSize = 100)
    }
}