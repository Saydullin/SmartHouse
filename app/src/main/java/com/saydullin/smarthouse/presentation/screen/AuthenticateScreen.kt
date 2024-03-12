package com.saydullin.smarthouse.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saydullin.smarthouse.domain.utils.StatusCode
import com.saydullin.smarthouse.presentation.component.ApartmentView
import com.saydullin.smarthouse.presentation.component.ErrorDialogView
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel

@Composable
fun AuthenticateScreen(
    apartmentViewModel: ApartmentViewModel = hiltViewModel()
) {

    apartmentViewModel.getAllRepository()
    val apartments = apartmentViewModel.apartments.value ?: listOf()
    val error = apartmentViewModel.error.value

    if (error != null && error != StatusCode.NO_ERROR) {
        ErrorDialogView(
            onDismissRequest = { apartmentViewModel.resetError() },
            onConfirmation = { apartmentViewModel.resetError() },
            dialogTitle = "Error",
            dialogText = "Something happend, ops $error",
            icon = Icons.Default.AccountBox
        )
    }
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(vertical = 18.dp),
    ) {
        items(apartments) { apartment ->
            ApartmentView(apartment = apartment)
        }
    }

}


