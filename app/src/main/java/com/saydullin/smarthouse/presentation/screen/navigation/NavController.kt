package com.saydullin.smarthouse.presentation.screen.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentInfo
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentsScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignInScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignUpScreen
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel

@Composable
fun NavController(
    authViewModel: AuthViewModel = hiltViewModel(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel(),
) {

    val auth = Firebase.auth
    val isAuthenticated = authViewModel.isAuthenticated.value
    val loading = authViewModel.loading.value
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        auth.currentUser?.reload()
    }

    auth.addAuthStateListener {
        if (auth.currentUser == null) {
            authViewModel.signOut()
        }
    }

    Log.e("sady", "User UID ${auth.currentUser?.uid}")

    NavHost(
        navController = navController,
        startDestination = Screen.Apartments.route,
    ) {
        composable(Screen.Apartments.route) {
            ApartmentsScreen(
                navController = navController,
                apartmentViewModel = apartmentViewModel,
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController = navController,
                authViewModel = authViewModel,
            )
        }
        composable(Screen.SignIn.route) {
            SignInScreen(
                navController = navController,
                authViewModel = authViewModel,
            )
        }
        composable(Screen.ApartmentInfo.route) {
            ApartmentInfo(
                navController = navController,
                apartmentViewModel = apartmentViewModel
            )
        }
    }

    if (!isAuthenticated) {
        navController.navigate(Screen.SignUp.route) {
            popUpTo(navController.currentDestination?.route ?: Screen.Apartments.route) {
                inclusive = true
            }
        }
    } else {
        navController.navigate(Screen.Apartments.route) {
            popUpTo(navController.currentDestination?.route ?: Screen.SignUp.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    if (loading) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }

}


