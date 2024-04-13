package com.saydullin.smarthouse.presentation.screen.apartment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.domain.model.ApartmentUserRate
import com.saydullin.smarthouse.presentation.component.rate.RateView
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel

@Composable
fun ApartmentInfo(
    navController: NavController = rememberNavController(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel()
) {

    val user = Firebase.auth.currentUser
    val apartment = apartmentViewModel.currentApartment.value

    if (apartment == null || user == null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "Error while loading apartment")
        }
        return
    }

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
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 18.dp)
        ) {
            items(apartment.images) {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = apartment.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(140.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        RateView(
            modifier = Modifier
                .padding(horizontal = 18.dp),
            onRate = { starCount, message ->
                apartmentViewModel.rateApartment(
                    ApartmentUserRate.getRate(
                        apartmentId = apartment.id,
                        authorUID = user.uid,
                        starCount = starCount,
                        message = message,
                    )
                )
            }
        )
    }

}


