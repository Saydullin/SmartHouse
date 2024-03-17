package com.saydullin.smarthouse.presentation.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.presentation.screen.authenticate.SignInScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignUpScreen

@Composable
fun NavController() {

    val auth = Firebase.auth
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SignUp.route,
    ) {
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController = navController
            )
        }
        composable(Screen.SignIn.route) {
            SignInScreen(
                navController = navController
            )
        }
    }

}