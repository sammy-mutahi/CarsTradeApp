package com.sammy.sell_data.data.search

data class SearchDto(
    val pagination: Pagination,
    val result: List<Result>
)