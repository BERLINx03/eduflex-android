package com.example.eduflex.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.eduflex.auth.presentation.screens.LoginScreen
import com.example.eduflex.auth.presentation.screens.RegisterScreen
import com.example.eduflex.auth.presentation.viewmodels.AuthViewModel
import com.example.eduflex.util.Screens
import com.example.eduflex.util.animatedComposable
import org.koin.androidx.compose.koinViewModel

@Composable
fun EduFlexNavHost() {
    val navController = rememberNavController()
    val authViewModel = koinViewModel<AuthViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        animatedComposable(Screens.LoginScreen.route) {
            LoginScreen(viewModel = authViewModel, onNavigateToRegister = {
                navController.navigate(
                    Screens.Register.route
                )
            }) {
                navController.navigate(Screens.Home.route)
            }
        }

        animatedComposable(Screens.Register.route) {
            RegisterScreen(viewModel = authViewModel, onNavigateToLogin = {
                navController.navigate(
                    Screens.LoginScreen.route
                )
            })
        }
    }
}