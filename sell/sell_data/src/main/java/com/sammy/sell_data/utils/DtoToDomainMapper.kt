package com.sammy.sell_data.utils

import com.sammy.sell_data.data.media.CarMedia
import com.sammy.sell_data.data.popular.Make
import com.sammy.sell_data.data.search.Result
import com.sammy.sell_domain.data.PopularCar
import com.sammy.sell_domain.data.SearchCarResult

fun List<Result>.toSearchResult(): List<SearchCarResult> {
    val result = mutableListOf<SearchCarResult>()
    this.forEach {
        result.add(
            SearchCarResult(
                bodyTypeId = it.bodyTypeId,
                city = it.city,
                depositReceived = it.depositReceived,
                fuelType = it.fuelType,
                gradeScore = it.gradeScore,
                hasFinancing = it.hasFinancing,
                hasThreeDImage = it.hasThreeDImage,
                hasWarranty = it.hasWarranty,
                id = it.id,
                imageUrl = it.imageUrl,
                installment = it.installment,
                licensePlate = it.licensePlate,
                loanValue = it.loanValue,
                marketplaceOldPrice = it.marketplaceOldPrice,
                marketplacePrice = it.marketplacePrice,
                marketplaceVisibleDate = it.marketplaceVisibleDate,
                mileage = it.mileage,
                mileageUnit = it.mileageUnit,
                sellingCondition = it.sellingCondition,
                sold = it.sold,
                state = it.state,
                title = it.title,
                transmission = it.transmission,
                websiteUrl = it.websiteUrl,
                year = it.year,
                appViewCount = it.stats.appViewCount,
                appViewerCount = it.stats.appViewerCount,
                interestCount = it.stats.interestCount,
                processedLoanCount = it.stats.processedLoanCount,
                testDriveCount = it.stats.testDriveCount,
                webViewCount = it.stats.webViewCount,
                webViewerCount = it.stats.webViewerCount
            )
        )
    }
    return result
}

internal fun List<Make>.toPopularCars(): List<PopularCar> {
    val result = mutableListOf<PopularCar>()
    this.forEach {
        PopularCar(
            it.id,
            it.imageUrl,
            it.name
        )
    }
    return result
}

internal fun List<CarMedia>.toCarMedia(): List<com.sammy.sell_domain.data.CarMedia> {
    val result = mutableListOf<com.sammy.sell_domain.data.CarMedia>()
    this.forEach {
        com.sammy.sell_domain.data.CarMedia(
            it.id,
            it.name,
            it.type,
            it.url
        )
    }
    return result
}
