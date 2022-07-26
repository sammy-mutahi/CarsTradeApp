package com.sammy.sell_data.network

import com.sammy.sell_data.data.popular.PopularCarsDto
import com.sammy.sell_data.data.search.SearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SellCarApi {

    @GET("/make")
    suspend fun getPopularCars(
        @Query("popular") popular: Boolean = true
    ): PopularCarsDto

    @GET("/car/search")
    suspend fun searchCars(
        @Query("query") query: String
    ): SearchDto

    @GET("/car/{carId}")
    suspend fun getCarDetails(
        @Path("carId") carId: String
    )

    @GET("/car_media")
    suspend fun getCarMedia(
        @Query("carId") carId: String
    )
}