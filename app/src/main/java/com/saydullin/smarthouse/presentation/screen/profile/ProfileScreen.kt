package com.saydullin.smarthouse.presentation.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.presentation.utils.ErrorMessage
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel
import com.saydullin.smarthouse.presentation.viewmodel.ProfileViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        profileViewModel.getUserData()
    }

    val userData = profileViewModel.userData.value
    val error = profileViewModel.error.value
    val isSaved = profileViewModel.isSaved.value
    val firstName = remember { mutableStateOf(userData?.firstName ?: "") }
    val middleName = remember { mutableStateOf(userData?.middleName ?: "") }
    val lastName = remember { mutableStateOf(userData?.lastName ?: "") }
    val username = remember { mutableStateOf(userData?.userName ?: "") }
    val image = remember { mutableStateOf(userData?.imageProfile ?: "") }
    val description = remember { mutableStateOf(userData?.description ?: "") }
    val email = remember { mutableStateOf(userData?.email ?: "") }
    val status = remember { mutableStateOf(userData?.status ?: "") }
    val phoneNumber = remember { mutableStateOf(userData?.phoneNumber ?: "") }
    val city = remember { mutableStateOf(userData?.city ?: "") }
    val country = remember { mutableStateOf(userData?.country ?: "") }

    if (isSaved != null) {
        AlertDialog(
            onDismissRequest = { profileViewModel.resetIsSaved() },
            confirmButton = {  },
            title = {
                Text(text = "Data is saved!")
            },
            text = {
                Text(text = "Profile data is successfully saved!")
            }
        )
    }

    if (error != null) {
        val errorMessage = ErrorMessage.execute(context = LocalContext.current, e = error)
        AlertDialog(
            onDismissRequest = { profileViewModel.resetError() },
            confirmButton = {  },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = errorMessage)
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 18.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 18.dp),
            text = "Profile",
            style = MaterialTheme.typography.bodyLarge
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "First name",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = firstName.value,
                onValueChange = { firstName.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.firstName ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Middle name",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = middleName.value,
                onValueChange = { middleName.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.middleName ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Last name",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = lastName.value,
                onValueChange = { lastName.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.lastName ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Username",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = username.value,
                onValueChange = { username.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.userName ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Status (looking for apartments, selling)",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = status.value,
                onValueChange = { status.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.status ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Email",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.email ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Description",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .height(100.dp),
                value = description.value,
                onValueChange = { description.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.description ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Phone number",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.phoneNumber ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Country",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = country.value,
                onValueChange = { country.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.country ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "City",
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                textStyle = MaterialTheme.typography.titleMedium,
                value = city.value,
                onValueChange = { city.value = it },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = userData?.city ?: ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                profileViewModel.saveUserData(
                    UserData(
                        firstName = firstName.value,
                        middleName = middleName.value,
                        lastName = lastName.value,
                        description = description.value,
                        email = email.value,
                        status = status.value,
                        phoneNumber = phoneNumber.value,
                        country = country.value,
                        city = city.value,
                        userName = username.value
                    )
                )
            }) {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(onClick = { authViewModel.logOut() }) {
                    Text(
                        text = "Log out",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Button(onClick = {
                    profileViewModel.dropUserData()
                    authViewModel.deleteAccount("saydullindev@gmail.com", "Saydullin")
                }) {
                    Text(
                        text = "Delete account",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}


