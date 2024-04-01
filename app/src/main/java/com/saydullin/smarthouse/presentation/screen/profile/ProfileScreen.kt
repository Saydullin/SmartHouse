package com.saydullin.smarthouse.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val firstName = remember { mutableStateOf("") }
    val middleName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val image = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 18.dp),
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 18.dp),
            text = "Profile",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            textStyle = MaterialTheme.typography.titleMedium,
            value = firstName.value,
            onValueChange = { firstName.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "First name"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            textStyle = MaterialTheme.typography.titleMedium,
            value = middleName.value,
            onValueChange = { middleName.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Middle name"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            textStyle = MaterialTheme.typography.titleMedium,
            value = lastName.value,
            onValueChange = { lastName.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Last name"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            textStyle = MaterialTheme.typography.titleMedium,
            value = email.value,
            onValueChange = { email.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Email"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .height(50.dp),
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Description"
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            authViewModel.logOut()
        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { authViewModel.logOut() }) {
            Text(text = "Log out")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { authViewModel.deleteAccount("saydullindev@gmail.com", "Saydullin") }) {
            Text(text = "Delete account")
        }
    }

}


