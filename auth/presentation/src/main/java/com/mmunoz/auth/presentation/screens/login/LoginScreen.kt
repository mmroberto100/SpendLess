package com.mmunoz.auth.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mmunoz.auth.presentation.R
import com.mmunoz.auth.presentation.components.AuthHeader
import com.mmunoz.auth.presentation.screens.login.components.LogInForm
import com.mmunoz.auth.presentation.screens.login.handling.LoginAction.NavigateToPinPrompt
import com.mmunoz.auth.presentation.screens.login.handling.LoginAction.NavigateToRegistration
import com.mmunoz.auth.presentation.screens.login.handling.LoginAction.NavigateToTransactions
import com.mmunoz.auth.presentation.screens.login.handling.LoginAction.RequestFocus
import com.mmunoz.auth.presentation.screens.login.handling.LoginUiEvent
import com.mmunoz.auth.presentation.screens.login.handling.LoginUiState
import com.mmunoz.core.presentation.designsystem.components.AppTextButton
import com.mmunoz.core.presentation.ui.ObserveAsActions
import com.mmunoz.core.presentation.ui.components.BaseContentLayout
import com.mmunoz.core.presentation.ui.components.LocalSystemIconsUiController
import com.mmunoz.core.presentation.ui.components.SystemIconsUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    navigateToTransactions: () -> Unit,
    navigateToRegistration: () -> Unit,
    navigateToPinPrompt: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    ObserveAsActions(viewModel.actions) { action ->
        when(action){
            RequestFocus -> focusRequester.requestFocus()
            NavigateToTransactions -> {
                focusManager.clearFocus()
                navigateToTransactions
            }
            NavigateToRegistration -> {
                focusManager.clearFocus()
                navigateToRegistration()
            }
            NavigateToPinPrompt -> navigateToPinPrompt
        }
    }

    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        focusRequester = focusRequester
    )
}

@Composable
private fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    focusRequester: FocusRequester
) {
    CompositionLocalProvider(
        LocalSystemIconsUiController provides SystemIconsUiController(
            isNavigationBarIconsDark = !state.showError
        )
    ) {
        BaseContentLayout(
            errorMessage = if (state.showError) stringResource(R.string.username_or_pin_is_incorrect) else null
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader(
                    title = stringResource(R.string.welcome_back),
                    description = stringResource(R.string.enter_you_login_details),
                    modifier = Modifier.padding(vertical = 36.dp)
                )

                LogInForm(
                    state = state,
                    onEvent = onEvent,
                    focusRequester = focusRequester
                )

                // Registration text button
                AppTextButton(
                    text = stringResource(R.string.new_to_spendless),
                    onClick = { onEvent(LoginUiEvent.RegistrationButtonClicked) },
                    modifier = Modifier.padding(top = 28.dp)
                )
            }
        }
    }
}