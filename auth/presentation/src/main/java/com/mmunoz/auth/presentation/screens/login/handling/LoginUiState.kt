package com.mmunoz.auth.presentation.screens.login.handling

import com.mmunoz.auth.domain.UserValidationState

data class LoginUiState(
    val username: String = "",
    val pin: String = "",
    val userValidationState: UserValidationState = UserValidationState(),
    val showError: Boolean = false
)