package com.sammy.sell_data.data.search

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)