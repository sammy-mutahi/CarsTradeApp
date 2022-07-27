package com.sammy.sell_presentation.utils

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<ViewState>?.loaded() {
    this?.postValue(ViewState.LOADED)
}

fun MutableLiveData<ViewState>?.loadedOrNoData(loaded: Boolean) {
    if (loaded) loaded() else noData()
}

fun MutableLiveData<ViewState>?.inProgress() {
    this?.postValue(ViewState.IS_LOADING)
}

fun MutableLiveData<ViewState>?.error() {
    this?.postValue(ViewState.LOADING_ERROR)
}

fun MutableLiveData<ViewState>?.noData() {
    this?.postValue(ViewState.NO_DATA)
}