package com.matttax.composefortvsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matttax.composefortvsample.data.RestaurantsRepository
import com.matttax.composefortvsample.ui.utils.ContentType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DishesViewModel(
    private val restaurantsRepository: RestaurantsRepository = RestaurantsRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(loading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        observeRestaurants()
    }

    private fun observeRestaurants() {
        viewModelScope.launch {
            restaurantsRepository.getAllRestaurants()
                .catch { ex ->
                    _uiState.value = HomeUiState(error = ex.message)
                }
                .collect { restaurants ->
                    _uiState.value = HomeUiState(
                        restaurants = restaurants,
                        openedRestaurant = restaurants.first()
                    )
                }
        }
    }

    fun setOpened(id: Long, contentType: ContentType) {
        val opened = uiState.value.restaurants.find { it.id == id }
        _uiState.value = _uiState.value.copy(
            openedRestaurant = opened,
            isDetailOnlyOpen = contentType == ContentType.SINGLE_PANE
        )
    }

    fun closeDetailScreen() {
        _uiState.value = _uiState
            .value.copy(
                isDetailOnlyOpen = false,
                openedRestaurant = _uiState.value.restaurants.first()
            )
    }
}
