package com.saydullin.smarthouse.presentation.screen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentInfo
import com.saydullin.smarthouse.presentation.screen.apartment.ApartmentsScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignInScreen
import com.saydullin.smarthouse.presentation.screen.authenticate.SignUpScreen
import com.saydullin.smarthouse.presentation.screen.profile.ProfileScreen
import com.saydullin.smarthouse.presentation.utils.ErrorMessage
import com.saydullin.smarthouse.presentation.viewmodel.ApartmentViewModel
import com.saydullin.smarthouse.presentation.viewmodel.AuthViewModel

@Composable
fun NavAuthController(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
    apartmentViewModel: ApartmentViewModel = hiltViewModel(),
) {

    val auth = Firebase.auth
    val authError = authViewModel.error.value
    val isAuthenticated = authViewModel.isAuthenticated.value
    val items = listOf(Screen.Apartments, Screen.Profile)

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
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

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
                            navController.navigate(screen.route)
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier
                .padding(bottom = padding.calculateBottomPadding()),
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
            val currentRoute = navController.currentDestination?.route ?: ""
            if (currentRoute != Screen.SignUp.route && currentRoute != Screen.SignIn.route) {
                navController.navigate(Screen.SignUp.route) {
                    popUpTo(navController.currentDestination?.route ?: Screen.Apartments.route) {
                        inclusive = true
                    }
                }
            }
        } else {
            navController.navigate(Screen.Apartments.route) {
                popUpTo(navController.currentDestination?.route ?: Screen.SignUp.route) {
                    inclusive = true
                }
            }
        }
    }

}


