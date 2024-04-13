package com.saydullin.smarthouse.presentation.component.rate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saydullin.smarthouse.presentation.model.ErrorInfo

@Composable
fun RateView(
    modifier: Modifier = Modifier,
    onRate: (starCount: Int, message: String) -> Unit
) {

    val isError = remember { mutableStateOf(ErrorInfo.empty) }
    val ratedStars = remember { mutableIntStateOf(0) }
    val message = remember { mutableStateOf("") }

    if (!isError.value.isHidden) {
        AlertDialog(
            onDismissRequest = { isError.value = ErrorInfo.empty },
            confirmButton = {  },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = isError.value.message)
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Оставьте свой отзыв",
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(5) { itemCount ->
                RateStar(
                    isSelectedStar = ratedStars.intValue > itemCount,
                    onClick = { ratedStars.intValue = itemCount + 1 }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.titleMedium,
            value = message.value,
            onValueChange = { message.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Comment message"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (ratedStars.intValue == 0) {
                    isError.value = ErrorInfo.setError("Star not rated")
                } else {
                    onRate(ratedStars.intValue, message.value)
                }
            }
        ) {
            Text(
                text = "Submit",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

}