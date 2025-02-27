package com.matttax.composefortvsample.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AvatarImage(
    drawableResource: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.clip(
            shape = RoundedCornerShape(12.dp)
        ),
        painter = painterResource(id = drawableResource),
        contentDescription = null,
    )
}
