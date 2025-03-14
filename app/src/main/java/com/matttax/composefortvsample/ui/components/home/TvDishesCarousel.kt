package com.matttax.composefortvsample.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.matttax.composefortvsample.data.model.Dish
import com.matttax.composefortvsample.ui.components.DishItemTv
import com.matttax.composefortvsample.ui.components.focus.FocusManager
import com.matttax.composefortvsample.ui.components.focus.FocusState
import com.matttax.composefortvsample.ui.components.focus.animateScrollAndCentralizeItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TvDishesCarousel(
    dishes: List<Dish>,
    onEntered: () -> Unit,
) {
    var lastSelected by remember { mutableIntStateOf(-1) }
    var focusState by remember { mutableStateOf(FocusState.CARD) }
    val focusManager = remember { FocusManager() }
    val listState = rememberLazyListState()
    LaunchedEffect(dishes) {
        focusManager.provideSize(dishes.size)
    }
    LaunchedEffect(lastSelected, focusState) {
        listState.animateScrollAndCentralizeItem(lastSelected.coerceAtLeast(0))
    }
    LazyRow(
        modifier = Modifier
            .focusProperties {
                enter = {
                    onEntered()
                    focusManager.cardFocusRequesters[lastSelected] ?: FocusRequester.Default
                }
                exit = {
                    lastSelected = -1
                    FocusRequester.Default
                }
            }
            .onKeyEvent { keyEvent ->
                if (keyEvent.nativeKeyEvent.action != android.view.KeyEvent.ACTION_DOWN || !focusManager.shouldProcess(keyEvent.key)) {
                    return@onKeyEvent false
                }
                focusManager
                    .processEvent(keyEvent.key, focusState, lastSelected, listState)
                    ?.let { newState ->
                        focusState = newState
                    }
                true
            },
        state = listState,
        userScrollEnabled = false,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        itemsIndexed(dishes) { idx, dish ->
            val focusRequester = remember { FocusRequester() }
            val rightButtonFocusRequester = remember { FocusRequester() }
            val leftButtonFocusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focusManager.apply {
                    cardFocusRequesters[idx] = focusRequester
                    rightButtonFocusRequesters[idx] = rightButtonFocusRequester
                    leftButtonFocusRequesters[idx] = leftButtonFocusRequester
                }
            }
            DishItemTv(
                modifier = Modifier.focusRequester(focusRequester),
                rightButtonFocusRequester = rightButtonFocusRequester,
                leftButtonFocusRequester = leftButtonFocusRequester,
                onSelected = {
                    focusState = FocusState.CARD
                    lastSelected = idx
                },
                onClick = {
                    focusManager.processCenter(idx)?.let {
                        focusState = it
                    }
                },
                isSelected = lastSelected == idx,
                dish = dish,
            )
        }
    }
}
