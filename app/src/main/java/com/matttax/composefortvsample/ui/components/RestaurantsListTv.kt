package com.matttax.composefortvsample.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.composefortvsample.data.model.Restaurant
import com.matttax.composefortvsample.ui.components.home.TvDishesCarousel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestaurantsListTv(restaurants: List<Restaurant>) {
    var selectedRow by remember { mutableIntStateOf(-1) }
    LazyColumn {
        stickyHeader {
            restaurants.getOrNull(selectedRow)?.let {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    text = it.name
                )
            }
        }
        itemsIndexed(restaurants) { idx, restaurant ->
            TvDishesCarousel(
                dishes = restaurant.dishes,
                onEntered = {
                    selectedRow = idx
                }
            )
        }
    }
}
