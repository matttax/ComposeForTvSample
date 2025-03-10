package com.matttax.composefortvsample.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon

@Composable
fun CardButton(
    modifier: Modifier,
    icon: ImageVector,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(2.dp),
        colors = ButtonDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .2f),
            focusedContainerColor = MaterialTheme.colorScheme.primary,
        ),
        onClick = { }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}
