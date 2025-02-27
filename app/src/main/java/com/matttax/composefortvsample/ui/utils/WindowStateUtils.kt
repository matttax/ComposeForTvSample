package com.matttax.composefortvsample.ui.utils

import androidx.window.layout.FoldingFeature

fun FoldingFeature.isBookPosture(): Boolean {
    return state == FoldingFeature.State.HALF_OPENED &&
        orientation == FoldingFeature.Orientation.VERTICAL
}

fun FoldingFeature.isSeparating(): Boolean {
    return state == FoldingFeature.State.FLAT && isSeparating
