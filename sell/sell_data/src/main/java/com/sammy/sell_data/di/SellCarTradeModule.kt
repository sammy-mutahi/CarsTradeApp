package com.sammy.sell_data.di

import com.sammy.sell_data.network.ApiService
import com.sammy.sell_data.repository.CarSearchRepository
import com.sammy.sell_domain.repository.CarMediaRepository
import com.sammy.sell_domain.repository.PopularCarRepository
import com.sammy.sell_domain.repository.SearchCarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SellCarTradeModule {
    private const val BASE_URL = "https://api-prod.autochek.africa/v1/inventory/"
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providePopularCarsRepository(apiService: ApiService): PopularCarRepository {
        return com.sammy.sell_data.repository.PopularCarRepository(apiService)
    }


    @Provides
    fun provideSearchCarRepository(apiService: ApiService): SearchCarRepository {
        return CarSearchRepository(apiService)
    }


    @Provides
    fun provideCarMediaRepository(apiService: ApiService): CarMediaRepository {
        return com.sammy.sell_data.repository.CarMediaRepository(apiService)
    }
}