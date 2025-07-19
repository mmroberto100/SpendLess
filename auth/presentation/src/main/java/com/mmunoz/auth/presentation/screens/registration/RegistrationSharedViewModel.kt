package com.mmunoz.auth.presentation.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmunoz.auth.domain.UserDataValidator
import com.mmunoz.auth.presentation.screens.registration.create_username.handling.CreateUsernameAction
import com.mmunoz.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiEvent
import com.mmunoz.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiState
import com.mmunoz.core.domain.repository.SessionRepository
import com.mmunoz.core.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationSharedViewModel(
    private val userDataValidator: UserDataValidator,
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    var usernameState by mutableStateOf(CreateUsernameUiState())
        private set

    private val _usernameActions = Channel<CreateUsernameAction>()
    val usernameActions = _usernameActions.receiveAsFlow()


    fun onEvent(event: CreateUsernameUiEvent) {
        when (event) {
            CreateUsernameUiEvent.NextButtonClicked -> validateUsername()
        }
    }

    private fun validateUsername() {
        usernameState = usernameState.copy(
            userValidationState = userDataValidator.validateUsername(usernameState.username.text.toString())
        )

        if (usernameState.userValidationState.isValidUsername) {
            viewModelScope.launch {
                val username = usernameState.username.text.toString()
                val user = userRepository.getUser(username)

                if (user != null) {
                    usernameState = usernameState.copy(isUsernameTaken = true)
                } else {
                    savedStateHandle[USERNAME_KEY] = username
                    _usernameActions.send(CreateUsernameAction.NavigateToCreatePinScreen)
                }
            }
        }
    }

    private companion object {
        const val USERNAME_KEY = "username_key"
        const val PIN_KEY = "pin_key"
    }
}
