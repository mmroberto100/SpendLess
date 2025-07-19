package com.mmunoz.spendless.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mmunoz.auth.presentation.screens.login.LoginScreenRoot
import com.mmunoz.auth.presentation.screens.registration.RegistrationSharedViewModel
import com.mmunoz.auth.presentation.screens.registration.create_username.CreateUsernameScreenRoot
import com.mmunoz.spendless.navigation.NavigationState
import com.mmunoz.spendless.navigation.routes.Feature
import com.mmunoz.spendless.navigation.routes.Screen
import com.mmunoz.spendless.navigation.sharedViewModel

fun NavGraphBuilder.authGraph(
    navigationState: NavigationState,
    isUserSessionExpired: Boolean
) {
    navigation(
        startDestination = if (isUserSessionExpired) Screen.PinPrompt.route else Screen.Login.route,
        route = Feature.Auth.route

    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreenRoot(
                navigateToTransactions = { navigationState.navigateToTransactions() },
                navigateToRegistration = { navigationState.navigateTo(Screen.CreateUsername.route) },
                navigateToPinPrompt = { navigationState.navigateToPinPrompt() }
            )
        }

        composable(
            route = Screen.CreateUsername.route
        ) { entry ->
            val registrationSharedViewModel =
                entry.sharedViewModel<RegistrationSharedViewModel>(
                    navigationState.navController
                )

            CreateUsernameScreenRoot(
                navigateToLogin = { navigationState.navigateToLogin() },
                navigateNext = { navigationState.navigateTo(Screen.CreatePIN.route) },
                viewModel = registrationSharedViewModel
            )
        }
    }
}