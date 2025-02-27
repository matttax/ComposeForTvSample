package com.matttax.composefortvsample.ui.utils

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.content.res.Configuration
import androidx.window.layout.FoldingFeature

fun FoldingFeature.isBookPosture(): Boolean {
    return state == FoldingFeature.State.HALF_OPENED &&
        orientation == FoldingFeature.Orientation.VERTICAL
}

fun FoldingFeature.isSeparating(): Boolean {
    return state == FoldingFeature.State.FLAT && isSeparating
}

fun Context.isTvDevice(): Boolean {
    val uiModeManager = getSystemService(UI_MODE_SERVICE) as? UiModeManager
    return uiModeManager?.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
}
