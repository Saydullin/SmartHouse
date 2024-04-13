package com.saydullin.smarthouse.presentation.component.rate

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun RateStar(
    isSelectedStar: Boolean = false,
    onClick: () -> Unit
) {

    Icon(
        modifier = Modifier
            .clickable { onClick() },
        imageVector = Icons.Default.Star,
        contentDescription = "Star",
        tint = if (isSelectedStar) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface
        },
    )

}


