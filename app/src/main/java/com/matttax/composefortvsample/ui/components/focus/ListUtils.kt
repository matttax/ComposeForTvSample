package com.matttax.composefortvsample.ui.components.focus

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.animateScrollAndCentralizeItem(
    index: Int,
    indexOnFallback: Int = index
) {
    layoutInfo.visibleItemsInfo
        .firstOrNull { it.index == index }?.let {
            val center = layoutInfo.viewportEndOffset / 2
            val childCenter = it.offset + it.size / 2
            val offsetValue = (childCenter - center).toFloat()
            animateScrollBy(
                value = offsetValue,
                animationSpec = tween(
                    durationMillis = 500
                )
            )
        } ?: run { animateScrollToItem(indexOnFallback) }
}
