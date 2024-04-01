package com.saydullin.smarthouse.presentation.screen.navigation

import com.saydullin.smarthouse.R

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

    data object AddApartment: Screen(
        route = "add_apartment",
        title = "Add Apartment",
        icon = 0
    )

    data object Profile: Screen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile
    )

    data object Apartments: Screen(
        route = "apartments",
        title = "Apartments",
        icon = R.drawable.home
    )

    data object ApartmentInfo: Screen(
        route = "apartment_info",
        title = "Apartment Info",
        icon = 0
    )

}