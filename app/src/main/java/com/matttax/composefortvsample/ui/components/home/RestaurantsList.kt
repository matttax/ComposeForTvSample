package com.matttax.composefortvsample.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matttax.composefortvsample.data.model.Restaurant
import com.matttax.composefortvsample.ui.components.DishesList
import com.matttax.composefortvsample.ui.components.RestaurantSearchBar
import com.matttax.composefortvsample.ui.utils.ContentType

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            restaurants = restaurants,
            onSearchItemSelected = { searchedEmail ->
                navigateToDetail(searchedEmail.id, ContentType.SINGLE_PANE)
            },
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
