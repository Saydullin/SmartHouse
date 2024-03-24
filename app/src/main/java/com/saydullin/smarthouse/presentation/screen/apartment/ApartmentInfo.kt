package com.saydullin.smarthouse.presentation.screen.apartment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel

@Composable
fun ApartmentInfo(
    navController: NavController = rememberNavController(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel()
) {

    val apartment = apartmentViewModel.currentApartment.value

    if (apartment == null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ) {
            Text(text = "Error while loading apartment")
        }
        return
    }

    apartmentViewModel.saveFavourite(
        ApartmentFavourite(
            id = 1,
            apartmentId = 1,
            uid = Firebase.auth.currentUser?.uid ?: ""
        )
    )

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
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = apartment.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}