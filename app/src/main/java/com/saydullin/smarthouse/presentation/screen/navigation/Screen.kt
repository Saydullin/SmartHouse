package com.saydullin.smarthouse.presentation.screen.navigation

sealed class Screen(
    val route: String,
    val title: String,
    val icon: Int
) {

    data object SignIn: Screen(
        route = "sign_in",
        title = "Sign In",
        icon = 0
    )

    data object SignUp: Screen(
        route = "sign_up",
        title = "Sign Up",
        icon = 0
    )

    data object Apartments: Screen(
        route = "apartments",
        title = "Apartments",
        icon = 0
    )

}