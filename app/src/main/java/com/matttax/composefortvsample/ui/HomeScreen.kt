package com.matttax.composefortvsample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.matttax.composefortvsample.presentation.HomeUiState
import com.matttax.composefortvsample.ui.components.RestaurantsListTv
import com.matttax.composefortvsample.ui.components.home.DishesPanel
import com.matttax.composefortvsample.ui.components.home.RestaurantsList
import com.matttax.composefortvsample.ui.components.home.SinglePaneContent
import com.matttax.composefortvsample.ui.utils.ContentType

@Composable
fun HomeScreen(
    contentType: ContentType,
    homeUiState: HomeUiState,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, ContentType) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(contentType) {
        if (contentType == ContentType.SINGLE_PANE && !homeUiState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }
    when (contentType) {
        ContentType.SINGLE_PANE -> Box(
            modifier = modifier.fillMaxSize()
        ) {
            SinglePaneContent(
                homeUIState = homeUiState,
                modifier = Modifier.fillMaxSize(),
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail
            )
        }
        ContentType.DUAL_PANE -> TwoPane(
            first = {
                RestaurantsList(
                    restaurants = homeUiState.restaurants,
                    openedRestaurant = homeUiState.openedRestaurant,
                    navigateToDetail = navigateToDetail
                )
            },
            second = {
                DishesPanel(
                    restaurant = homeUiState.openedRestaurant ?: homeUiState.restaurants.first(),
                    isFullScreen = false
                )
            },
            strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f, gapWidth = 16.dp),
            displayFeatures = displayFeatures
        )
        ContentType.TV -> RestaurantsListTv(
            restaurants = homeUiState.restaurants
        )
    }
}
