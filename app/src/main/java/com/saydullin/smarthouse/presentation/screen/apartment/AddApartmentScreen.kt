package com.saydullin.smarthouse.presentation.screen.apartment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel

@Composable
fun AddApartmentScreen(
    navController: NavController = rememberNavController(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel(),
) {

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val image = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            placeholder = {
                Text(text = "Title")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = {
                Text(text = "Description")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = image.value,
            onValueChange = { image.value = it },
            placeholder = {
                Text(text = "Image")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            apartmentViewModel.addApartment(
                Apartment(
                    id = 3,
                    title = title.value,
                    description = description.value,
                    previewImage = image.value,
                )
            )
        }) {
            Text(text = "Add")
        }
    }

}