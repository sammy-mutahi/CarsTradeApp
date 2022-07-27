package com.sammy.sell_data.repository


import androidx.paging.*
import com.sammy.sell_data.network.ApiService
import com.sammy.sell_data.utils.toPopularCars
import com.sammy.sell_domain.data.PopularCar
import com.sammy.sell_domain.repository.PopularCarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularCarRepository @Inject constructor(
    private val apiService: ApiService
) : PopularCarRepository {

    override suspend fun popularCars(
        isPopular: Boolean,
        pageSize: Int
    ): Flow<PagingData<PopularCar>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                PopularCarsSource(apiService, isPopular, pageSize)
            }
        ).flow
    }
}

private class PopularCarsSource(
    private val apiService: ApiService,
    private val isPopular: Boolean,
    private val pageSize: Int
) :
    PagingSource<Int, PopularCar>() {
    private var totalPagesLocal = 1
    override fun getRefreshKey(state: PagingState<Int, PopularCar>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularCar> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getPopularCars(
                popular = isPopular,
                pageNumber = 1,
                pageSize = pageSize
            )
            totalPagesLocal = response.pagination.total
            val nextKey = if (totalPagesLocal < response.pagination.total) {
                position + (params.loadSize / pageSize)
            } else {
                null
            }
            val popularCars = response.makeList.toPopularCars()
            LoadResult.Page(
                data = popularCars,
                prevKey = params.key,
                nextKey = nextKey
            )
        } catch (e: Throwable) {
            return LoadResult.Error(e)
        }
    }

}

