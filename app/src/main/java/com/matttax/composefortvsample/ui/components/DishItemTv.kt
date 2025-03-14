package com.matttax.composefortvsample.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
import com.matttax.composefortvsample.data.model.Dish

@Composable
fun DishItemTv(
    dish: Dish,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onClick: () -> Unit,
    rightButtonFocusRequester: FocusRequester,
    leftButtonFocusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .height(330.dp)
                .onFocusChanged {
                    if (it.isFocused) {
                        onSelected()
                    }
                },
            glow = CardDefaults.glow(
                focusedGlow = Glow(
                    elevationColor = MaterialTheme.colorScheme.outline,
                    elevation = 5.dp,
                )
            ),
            border = CardDefaults.border(
                focusedBorder = Border(
                    border = BorderStroke(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
                border = Border.None
            ),
            scale = if (isSelected) {
                CardDefaults.scale(
                    scale = 1.1f,
                    focusedScale = 1.1f
                )
            } else {
                CardDefaults.scale(
                    scale = 1f,
                    focusedScale = 1.1f,
                )
            },
            onClick = onClick
        ) {
            AvatarImage(
                modifier = Modifier.size(180.dp),
                drawableResource = dish.logoResId,
            )
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            Text(
                modifier = Modifier
                    .width(160.dp)
                    .align(Alignment.CenterHorizontally),
                text = dish.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            AnimatedVisibility(
                visible = isSelected,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Row(
                    modifier = Modifier
                        .width(180.dp)
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardButton(
                        modifier = Modifier.size(35.dp)
                            .focusRequester(leftButtonFocusRequester),
                        icon = Icons.Default.Remove
                    )
                    Text(
                        text = "0"
                    )
                    CardButton(
                        modifier = Modifier.size(35.dp)
                            .focusRequester(rightButtonFocusRequester),
                        icon = Icons.Default.Add
                    )
                }
            }
        }
    }
}
