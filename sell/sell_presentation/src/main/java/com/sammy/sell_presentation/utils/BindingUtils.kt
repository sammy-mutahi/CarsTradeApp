package com.sammy.sell_presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["visibleOnState", "visibleCondition"], requireAll = true)
fun visibleOnState(view: View, visibleOnState: ViewState?, visibleCondition: ViewState?) {
    view.visibility = if (visibleCondition == visibleOnState) View.VISIBLE else View.GONE
}

/*
 Allow to hide but not remove from layout (helpfull to keep height in layout)
 */
@BindingAdapter(value = ["visibilityOnState", "visibilityCondition"], requireAll = true)
fun visibilityOnState(view: View, visibilityOnState: ViewState?, visibilityCondition: ViewState) {
    view.visibility = if (visibilityCondition == visibilityOnState) View.VISIBLE else View.INVISIBLE
    view.requestLayout()
}