package com.sammy.sell_data.repository

import androidx.paging.*
import com.sammy.sell_data.network.ApiService
import com.sammy.sell_data.utils.toCarMedia
import com.sammy.sell_domain.data.CarMedia
import com.sammy.sell_domain.repository.CarMediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarMediaRepository @Inject constructor(
    private val apiService: ApiService
) : CarMediaRepository {
    override suspend fun getCarMedia(carId: String, pageSize: Int): Flow<PagingData<CarMedia>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                CarMediaPagingSource(apiService, carId, pageSize)
            }
        ).flow
    }
}

private class CarMediaPagingSource(
    private val apiService: ApiService,
    private val carId: String,
    private val pageSize: Int
) : PagingSource<Int, CarMedia>() {
    private var totalLocalPages = 1
    override fun getRefreshKey(state: PagingState<Int, CarMedia>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CarMedia> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getCarMedia(carId, pageNumber = 1, pageSize = pageSize)
            totalLocalPages = response.pagination.total
            val nextkey = if (totalLocalPages < response.pagination.total) {
                position + (params.loadSize / pageSize)
            } else {
                null
            }
            val carMedia = response.carMediaList.toCarMedia()
            LoadResult.Page(
                data = carMedia,
                prevKey = params.key,
                nextKey = nextkey
            )
        } catch (e: Throwable) {
            return LoadResult.Error(e)
        }
    }

}