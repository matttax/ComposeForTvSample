package com.matttax.composefortvsample.data

import com.matttax.composefortvsample.data.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RestaurantsRepository {

    fun getAllRestaurants(): Flow<List<Restaurant>> = flow {
        emit(LocalDataProvider.restaurants)
    }
}
