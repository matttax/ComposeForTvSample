package com.matttax.composefortvsample.data.model

import androidx.annotation.DrawableRes

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    val isStarred: Boolean = false,
    val dishes: List<Dish> = emptyList(),
    @DrawableRes val logoResId: Int,
)
