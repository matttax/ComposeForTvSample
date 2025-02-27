package com.matttax.composefortvsample.ui.components.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matttax.composefortvsample.presentation.HomeUiState
import com.matttax.composefortvsample.ui.utils.ContentType

@Composable
fun SinglePaneContent(
    homeUIState: HomeUiState,
    modifier: Modifier = Modifier,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, ContentType) -> Unit
) {
    if (homeUIState.openedRestaurant != null && homeUIState.isDetailOnlyOpen) {
        BackHandler {
            closeDetailScreen()
        }
        DishesPanel(restaurant = homeUIState.openedRestaurant) {
            closeDetailScreen()
        }
    } else {
        RestaurantsList(
            restaurants = homeUIState.restaurants,
            openedRestaurant = homeUIState.openedRestaurant,
            modifier = modifier,
            navigateToDetail = navigateToDetail
        )
    }
}
