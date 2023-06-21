package com.my.weather.util

import android.view.View

fun showViews(vararg views: View) {
    views.forEach { it.visibility = View.VISIBLE }
}

fun hideViews(vararg views: View) {
    views.forEach { it.visibility = View.GONE }
}

fun makeViewsInvisible(vararg views: View) {
    views.forEach { it.visibility = View.INVISIBLE }
}