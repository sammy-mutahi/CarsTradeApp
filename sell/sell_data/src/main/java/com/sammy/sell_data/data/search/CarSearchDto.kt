package com.sammy.sell_data.data.search

data class CarSearchDto(
    val pagination: Pagination,
    val result: List<Result>
)