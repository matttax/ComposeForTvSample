package com.matttax.composefortvsample.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.matttax.composefortvsample.data.model.Restaurant
import com.matttax.composefortvsample.ui.components.DishItem
import com.matttax.composefortvsample.ui.components.RestaurantDetailAppBar

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
