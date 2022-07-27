package com.sammy.sell_presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sammy.sell_domain.data.PopularCar
import com.sammy.sell_domain.data.SearchCarResult
import com.sammy.sell_domain.use_case.SellCarsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases: SellCarsUseCases
) : ViewModel() {

    suspend fun searchCar(query: String): Flow<PagingData<SearchCarResult>> {
        return useCases.searchCarUseCase.invoke(query).cachedIn(viewModelScope)
    }

    suspend fun getPopularCars(): Flow<PagingData<PopularCar>> {
        return useCases.popularCarsUseCase.invoke().cachedIn(viewModelScope)
    }
}