package com.matttax.composefortvsample.data.model

import androidx.annotation.DrawableRes

data class Dish(
    val id: Long,
    val name: String,
    val description: String,
    val isStarred: Boolean = false,
    val threads: List<Restaurant> = emptyList(),
    @DrawableRes val logoResId: Int,
)
