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
fun SignUpScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

//    val isAuthenticated = authViewModel.isAuthenticated.value
//    if (!isAuthenticated) {
//        authViewModel.signUp("saydullindev@gmail.com", "saydullindev")
//    }
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
            text = "Sign Up"
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = "Email",
            onValueChange = {  }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = "Password",
            onValueChange = {  }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = "Confirm password",
            onValueChange = {  }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {  }
        ) {
            Text("Done")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Signed in already?"
        )
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.SignIn.route)
                },
            text = "Sign In",
            color = Color.Blue
        )
    }

}


