package com.sammy.sell_domain.use_case

data class SellCarsUseCases(
    val carMediaUseCase: GetCarMedia,
    val popularCarsUseCase: GetPopularCars,
    val searchCarUseCase: SearchCar
)
