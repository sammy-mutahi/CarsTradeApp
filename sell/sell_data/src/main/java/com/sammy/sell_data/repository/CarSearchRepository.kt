package com.sammy.sell_data.repository

import android.util.Log
import androidx.paging.*
import com.sammy.sell_data.network.ApiService
import com.sammy.sell_data.utils.toSearchResult
import com.sammy.sell_domain.data.SearchCarResult
import com.sammy.sell_domain.repository.SearchCarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarSearchRepository @Inject constructor(
    private val apiService: ApiService
) : SearchCarRepository {

    override suspend fun searchCar(
        query: String,
        pageSize: Int
    ): Flow<PagingData<SearchCarResult>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                CarSearchResultsSource(query, apiService, pageSize)
            }
        ).flow
    }


}

class CarSearchResultsSource(
    private val query: String,
    private val apiService: ApiService,
    private val pageSize: Int
) : PagingSource<Int, SearchCarResult>() {
    private var totalLocalPages = 1
    override fun getRefreshKey(state: PagingState<Int, SearchCarResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchCarResult> {
        val position = params.key ?: 1
        return try {
            val response = apiService.searchCars(query = query, pageNumber = 1, pageSize = pageSize)
            totalLocalPages = response.pagination.total
            val nextkey = if (totalLocalPages < response.pagination.total) {
                position + (params.loadSize / pageSize)
            } else {
                null
            }
            val searchResult = response.result.toSearchResult()
            LoadResult.Page(
                data = searchResult,
                prevKey = params.key,
                nextKey = nextkey
            )
        } catch (e: Throwable) {
            Log.e("Repository", "CarSearchError: ${e.message}")
            return LoadResult.Error(e)
        }
    }

}
