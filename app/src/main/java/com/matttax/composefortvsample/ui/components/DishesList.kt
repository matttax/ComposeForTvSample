package com.matttax.composefortvsample.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.matttax.composefortvsample.data.model.Restaurant

@Composable
fun DishesList(
    restaurant: Restaurant,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    isOpened: Boolean = false,
) {
    Card(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
            .clickable { navigateToDetail(restaurant.id) }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = if (isOpened) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AvatarImage(
                modifier = Modifier.weight(0.3f),
                drawableResource = restaurant.logoResId,
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(0.55f)
                    .padding(15.dp),
                text = restaurant.name,
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(
                onClick = { },
                modifier = Modifier
                    .weight(0.15f)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = if (restaurant.isStarred) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                )
            }
        }
    }
}
