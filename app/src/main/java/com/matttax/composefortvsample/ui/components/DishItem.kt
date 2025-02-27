package com.matttax.composefortvsample.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matttax.composefortvsample.R
import com.matttax.composefortvsample.data.model.Dish

@Composable
fun DishItem(
    dish: Dish,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                AvatarImage(
                    modifier = Modifier.weight(0.4f),
                    drawableResource = dish.logoResId,
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(0.6f),
                ) {
                    Text(
                        text = dish.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(
                            top = 12.dp,
                            bottom = 8.dp
                        ),
                    )
                    Text(
                        text = dish.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp,
                        bottom = 8.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(.5f)
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterButton(R.drawable.ic_baseline_minus_24)
                    Text(
                        text = 1.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    CounterButton(R.drawable.ic_baseline_plus_24)
                }
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_cart),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun CounterButton(
    resourceId: Int,
) {
    OutlinedButton(
        modifier = Modifier.size(32.dp),
        contentPadding = PaddingValues(1.dp),
        onClick = { }
    ) {
        Icon(
            painter = painterResource(resourceId),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}
