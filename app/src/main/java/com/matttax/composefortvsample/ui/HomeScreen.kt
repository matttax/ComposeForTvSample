package com.matttax.composefortvsample.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.matttax.composefortvsample.data.model.Restaurant
import com.matttax.composefortvsample.presentation.HomeUiState
import com.matttax.composefortvsample.ui.components.DishItem
import com.matttax.composefortvsample.ui.components.DishesList
import com.matttax.composefortvsample.ui.components.RestaurantDetailAppBar
import com.matttax.composefortvsample.ui.components.RestaurantSearchBar
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
    if (contentType == ContentType.DUAL_PANE) {
        TwoPane(
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
    } else {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            ReplySinglePaneContent(
                homeUIState = homeUiState,
                modifier = Modifier.fillMaxSize(),
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun ReplySinglePaneContent(
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

@Composable
fun RestaurantsList(
    restaurants: List<Restaurant>,
    openedRestaurant: Restaurant?,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long, ContentType) -> Unit
) {
    Box(
        modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) {
        RestaurantSearchBar(
            restaurants = restaurants,
            onSearchItemSelected = { searchedEmail ->
                navigateToDetail(searchedEmail.id, ContentType.SINGLE_PANE)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
        ) {
            items(
                items = restaurants,
                key = { it.id }
            ) { restaurant ->
                DishesList(
                    restaurant = restaurant,
                    navigateToDetail = { id ->
                        navigateToDetail(id, ContentType.SINGLE_PANE)
                    },
                    isOpened = openedRestaurant?.id == restaurant.id,
                )
            }
            item {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }
        }
    }
}

@Composable
fun DishesPanel(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = true,
    onBackPressed: () -> Unit = {}
) {
    val listState = rememberLazyListState()
    LaunchedEffect(restaurant) {
        listState.animateScrollToItem(0)
    }
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        state = listState
    ) {
        item {
            RestaurantDetailAppBar(restaurant, isFullScreen) {
                onBackPressed()
            }
        }
        items(
            items = restaurant.dishes,
            key = { it.id }
        ) { dish ->
            DishItem(dish)
        }
        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}
