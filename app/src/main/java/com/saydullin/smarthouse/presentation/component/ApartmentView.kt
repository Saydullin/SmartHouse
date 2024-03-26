package com.saydullin.smarthouse.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.saydullin.smarthouse.domain.model.Apartment

@Composable
fun ApartmentView(
    apartment: Apartment,
    onClick: () -> Unit
) {

    Card(onClick = { onClick() }) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(apartment.previewImage),
                contentDescription = apartment.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = apartment.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = apartment.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }

}


