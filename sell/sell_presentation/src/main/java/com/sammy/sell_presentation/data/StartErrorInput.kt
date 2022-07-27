package com.sammy.sell_presentation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class StartErrorInput(
    val title: String,
    val description: String,
    val icon: Int,
) : Parcelable