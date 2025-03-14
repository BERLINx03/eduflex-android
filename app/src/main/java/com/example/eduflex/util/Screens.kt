package com.example.eduflex.util

sealed class Screens(val route: String) {
    data object LoginScreen: Screens("login_screen")
    data object Register: Screens("register_screen")
    data object Home: Screens("home_screen")
    data object Profile: Screens("profile_screen")
    data object Settings: Screens("settings_screen")
}