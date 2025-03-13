package com.matttax.composefortvsample.ui.components.focus

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key

class FocusManager {

    val cardFocusRequesters = mutableMapOf<Int, FocusRequester>()
    val rightButtonFocusRequesters = mutableMapOf<Int, FocusRequester>()
    val leftButtonFocusRequesters = mutableMapOf<Int, FocusRequester>()
    var externalFocusRequester: FocusRequester? = null

    private var collectionSize: Int = 0

    fun processEvent(direction: Key, currentState: FocusState, selected: Int, listState: LazyListState): FocusState? {
        return with(currentState) {
            when(direction) {
                Key.DirectionRight -> processRight(selected, listState)
                Key.DirectionLeft -> processLeft(selected, listState)
                Key.DirectionUp -> processUp(selected)
                Key.DirectionDown -> processDown(selected)
                else -> null
            }
        }
    }

    fun provideSize(newSize: Int) {
        collectionSize = newSize
    }

    private fun FocusState.processRight(selected: Int, listState: LazyListState): FocusState? {
        if (equals(FocusState.LEFT_BUTTON)) {
            rightButtonFocusRequesters[selected]
                ?.requestFocus()
                ?.also { return FocusState.RIGHT_BUTTON }
        } else if (selected == collectionSize || selected < listState.firstVisibleItemIndex + 5) {
            cardFocusRequesters[selected + 1]?.requestFocus()
        }
        return null
    }

    private fun FocusState.processLeft(selected: Int, listState: LazyListState): FocusState? {
        if (equals(FocusState.RIGHT_BUTTON)) {
            leftButtonFocusRequesters[selected]
                ?.requestFocus()
                ?.also { return FocusState.LEFT_BUTTON }
        } else if (selected > listState.firstVisibleItemIndex) {
            cardFocusRequesters[selected - 1]?.requestFocus()
        }
        return null
    }

    private fun FocusState.processUp(selected: Int): FocusState? {
        if (equals(FocusState.LEFT_BUTTON) || equals(FocusState.RIGHT_BUTTON)) {
            cardFocusRequesters[selected]?.requestFocus()
        }
        return null
    }

    private fun FocusState.processDown(selected: Int): FocusState? {
        if (equals(FocusState.CARD)) {
            rightButtonFocusRequesters[selected]
                ?.requestFocus()
                ?.also { return FocusState.RIGHT_BUTTON }
        } else {
            externalFocusRequester?.requestFocus()
        }
        return null
    }
}
