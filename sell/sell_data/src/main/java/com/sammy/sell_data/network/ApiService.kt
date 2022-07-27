package com.sammy.sell_data.network

import com.sammy.sell_data.data.media.CarMediaListDto
import com.sammy.sell_data.data.popular.PopularCarsDto
import com.sammy.sell_data.data.search.CarSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("make")
    suspend fun getPopularCars(
        @Query("popular") popular: Boolean,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): PopularCarsDto

    @GET("car/search")
    suspend fun searchCars(
        @Query("query") query: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): CarSearchDto

    @GET("car/{carId}")
    suspend fun getCarDetails(
        @Path("carId") carId: String
    )

    @GET("car_media")
    suspend fun getCarMedia(
        @Query("carId") carId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): CarMediaListDto
}