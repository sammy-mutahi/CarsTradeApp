package com.sammy.sell_presentation.utils

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder

object ImageLoader {
    fun getCoilImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}