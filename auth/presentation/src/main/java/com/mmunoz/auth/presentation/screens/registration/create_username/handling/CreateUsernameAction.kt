package com.mmunoz.auth.presentation.screens.registration.create_username.handling

sealed interface CreateUsernameAction {
    data object NavigateToCreatePinScreen : CreateUsernameAction
}