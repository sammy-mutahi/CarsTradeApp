package com.sammy.sell_domain.di

import com.sammy.sell_domain.repository.CarMediaRepository
import com.sammy.sell_domain.repository.PopularCarRepository
import com.sammy.sell_domain.repository.SearchCarRepository
import com.sammy.sell_domain.use_case.GetCarMedia
import com.sammy.sell_domain.use_case.GetPopularCars
import com.sammy.sell_domain.use_case.SearchCar
import com.sammy.sell_domain.use_case.SellCarsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideUseCases(
        mediaRepository: CarMediaRepository,
        popularCarRepository: PopularCarRepository,
        searchRepository: SearchCarRepository
    ): SellCarsUseCases = SellCarsUseCases(
        carMediaUseCase = GetCarMedia(mediaRepository),
        searchCarUseCase = SearchCar(searchRepository),
        popularCarsUseCase = GetPopularCars(popularCarRepository)
    )
}