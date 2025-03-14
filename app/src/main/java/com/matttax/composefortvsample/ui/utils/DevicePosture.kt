package com.matttax.composefortvsample.ui.utils

import android.graphics.Rect
import androidx.window.layout.FoldingFeature

sealed interface DevicePosture {
    data object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}
