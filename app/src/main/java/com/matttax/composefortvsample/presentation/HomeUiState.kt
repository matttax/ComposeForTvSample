package com.matttax.composefortvsample.presentation

import com.matttax.composefortvsample.data.model.Restaurant

data class HomeUiState(
    val restaurants: List<Restaurant> = emptyList(),
    val openedRestaurant: Restaurant? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)
