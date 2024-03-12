package com.saydullin.smarthouse.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel

@Composable
fun AuthenticateScreen(
    apartmentViewModel: ApartmentViewModel = hiltViewModel()
) {

    apartmentViewModel.getAllRepository()
    val apartments = apartmentViewModel.apartments.value
    val error = apartmentViewModel.error.value

    Text(text = "Authenticate Screen ${apartments?.get(0)?.title}, error: ${error.toString()}")

}


