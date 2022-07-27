package com.sammy.sell_presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideSoftKeyboard() {
    val imm: InputMethodManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.applicationWindowToken, 0)
}

fun View.remove() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}