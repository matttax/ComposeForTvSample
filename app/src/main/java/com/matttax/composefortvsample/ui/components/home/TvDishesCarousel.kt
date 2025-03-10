package com.matttax.composefortvsample.ui.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Surface
import com.matttax.composefortvsample.R
import com.matttax.composefortvsample.data.model.Dish
import com.matttax.composefortvsample.ui.components.DishItemTv

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TvDishesCarousel(
    dishes: List<Dish>,
) {
    var lastSelected by remember { mutableIntStateOf(-1) }
    var focusOnButtons by remember { mutableStateOf(false) }
    val focusRequesters = remember { mutableStateMapOf<Int, FocusRequester>() }
    val cardButtonFocusRequesters = remember { mutableStateMapOf<Int, FocusRequester>() }
    val externalButtonFocusRequester = remember { FocusRequester() }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier
                .align(Alignment.Center)
                .focusProperties {
                    enter = {
                        focusOnButtons = false
                        focusRequesters[lastSelected] ?: FocusRequester.Default
                    }
                    exit = {
                        if (!focusOnButtons) {
                            focusOnButtons = true
                            cardButtonFocusRequesters[lastSelected] ?: FocusRequester.Default
                        } else {
                            externalButtonFocusRequester
                        }
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            itemsIndexed(dishes) { idx, dish ->
                val focusRequester = remember { FocusRequester() }
                val buttonFocusRequester = remember { FocusRequester() }
                LaunchedEffect(Unit) {
                    focusRequesters[idx] = focusRequester
                    cardButtonFocusRequesters[idx] = buttonFocusRequester
                }
                DishItemTv(
                    modifier = Modifier.focusRequester(focusRequester),
                    buttonFocusRequester = buttonFocusRequester,
                    onSelected = { lastSelected = idx },
                    isSelected = lastSelected == idx,
                    dish = dish,
                )
            }
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .align(Alignment.BottomCenter),
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 300,
                )
            ),
            exit = fadeOut(),
            visible = lastSelected != -1
        ) {
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .focusRequester(externalButtonFocusRequester),
                colors = ButtonDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .7f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                ),
                scale = ButtonDefaults.scale(
                    focusedScale = 1.1f
                ),
                onClick = { }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.make_order),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }
}
