package com.saydullin.smarthouse.presentation.screen.authenticate

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.saydullin.smarthouse.presentation.screen.navigation.Screen
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel

@Composable
fun SignInScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val imageLogo = "https://pfst.cf2.poecdn.net/base/image/3565901aa1ae3a75945d642b6710c3549ee1cee21f505f8ce86cfa4ccc27818f"

    Column(
        modifier = Modifier.padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .width(70.dp)
                .height(70.dp),
            painter = rememberAsyncImagePainter(imageLogo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "Sign In"
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = login.value,
            placeholder = {
                Text(text = "Email")
            },
            onValueChange = {
                login.value = it
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password.value,
            placeholder = {
                Text(text = "Password")
            },
            onValueChange = {
                password.value = it
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                authViewModel.signIn(
                    login = login.value,
                    password = password.value
                )
            }
        ) {
            Text("Done")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Not registered yet?"
        )
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(navController.currentDestination?.route ?: Screen.SignIn.route) {
                            inclusive = true
                        }
                    }
                },
            text = "Sign Up",
            color = Color.Blue
        )
    }

}