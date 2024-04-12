package com.saydullin.smarthouse.presentation.screen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.presentation.screen.apartment.AddApartmentScreen
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentInfo
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentsScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignInScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignUpScreen
import com.saydullin.smarthouse.presentation.screen.profile.ProfileScreen
import com.saydullin.smarthouse.presentation.utils.ErrorMessage
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel
import com.saydullin.smarthouse.presentation.viewmodel.ProfileViewModel

@Composable
fun NavController(
    authViewModel: AuthViewModel = hiltViewModel(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val auth = Firebase.auth
    val authError = authViewModel.error.value
    val isAuthenticated = authViewModel.isAuthenticated.value
    val navController = rememberNavController()
    val items = listOf(Screen.Apartments, Screen.Profile)
    val excludeRoutes = listOf(Screen.SignUp.route, Screen.SignIn.route)

    LaunchedEffect(Unit) {
        auth.currentUser?.reload()
    }

    auth.addAuthStateListener {
        if (auth.currentUser == null) {
            authViewModel.resetAuth()
        }
    }

    if (authError != null) {
        val errorMessage = ErrorMessage.execute(context = LocalContext.current, e = authError)
        AlertDialog(
            onDismissRequest = { authViewModel.resetError() },
            confirmButton = {  },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = errorMessage)
            },

        )
    }

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val currentDest = currentDestination?.route ?: ""

            if (currentDest !in excludeRoutes) {
                BottomNavigation {
                    items.forEach { screen ->
                        val isSelected = currentDestination?.route == screen.route
                        BottomNavigationItem(
                            modifier = Modifier
                                .background(Color.White),
                            icon = {
                                Icon(
                                    painter = if (isSelected) {
                                        painterResource(screen.icon)
                                    } else {
                                        painterResource(screen.icon)
                                    },
                                    contentDescription = screen.title,
                                    tint = if (isSelected) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurface
                                    }
                                )
                            },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.currentDestination?.route ?: screen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        val currentDest = navController.currentDestination?.route ?: ""
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (currentDest !in excludeRoutes)
                        padding.calculateBottomPadding()
                    else
                        0.dp
                ),
            navController = navController,
            startDestination = Screen.Apartments.route,
        ) {
            composable(Screen.Apartments.route) {
                ApartmentsScreen(
                    navController = navController,
                    apartmentViewModel = apartmentViewModel,
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = navController,
                    authViewModel = authViewModel,
                    profileViewModel = profileViewModel,
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
            composable(Screen.AddApartment.route) {
                AddApartmentScreen(
                    navController = navController,
                    apartmentViewModel = apartmentViewModel
                )
            }
        }

        val currentRoute = navController.currentDestination?.route ?: ""
        if (!isAuthenticated) {
            if (currentRoute != Screen.SignUp.route && currentRoute != Screen.SignIn.route) {
                navController.navigate(Screen.SignUp.route) {
                    popUpTo(navController.currentDestination?.route ?: Screen.Apartments.route) {
                        inclusive = true
                    }
                }
            }
        } else {
            if (currentRoute != Screen.Apartments.route) {
                navController.navigate(Screen.Apartments.route) {
                    popUpTo(navController.currentDestination?.route ?: Screen.SignUp.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

}


