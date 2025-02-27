package com.matttax.composefortvsample.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.matttax.composefortvsample.presentation.HomeUiState
import com.matttax.composefortvsample.ui.utils.ContentType
import com.matttax.composefortvsample.ui.utils.DevicePosture
import com.matttax.composefortvsample.ui.utils.isBookPosture
import com.matttax.composefortvsample.ui.utils.isSeparating
import com.matttax.composefortvsample.ui.utils.isTvDevice

@Composable
fun DishesApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    homeUIState: HomeUiState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (Long, ContentType) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    val devicePosture = when {
        foldingFeature?.isBookPosture() == true -> DevicePosture.BookPosture(foldingFeature.bounds)
        foldingFeature?.isSeparating() == true -> DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
        else -> DevicePosture.NormalPosture
    }
    val contentType = when {
        context.isTvDevice() -> ContentType.TV
        windowSize.widthSizeClass == WindowWidthSizeClass.Expanded
                || (windowSize.widthSizeClass == WindowWidthSizeClass.Medium && devicePosture != DevicePosture.NormalPosture) -> ContentType.DUAL_PANE
        else -> ContentType.SINGLE_PANE
    }
    Surface {
        HomeScreen(
            contentType = contentType,
            homeUiState = homeUIState,
            displayFeatures = displayFeatures,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
        )
    }
}
