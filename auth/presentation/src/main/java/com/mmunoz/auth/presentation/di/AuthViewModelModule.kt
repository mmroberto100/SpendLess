package com.mmunoz.auth.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.mmunoz.auth.presentation.screens.login.LoginViewModel
import com.mmunoz.auth.presentation.screens.registration.RegistrationSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationSharedViewModel)

    viewModel { (savedStateHandle: SavedStateHandle) ->
        RegistrationSharedViewModel(
            userDataValidator = get(),
            savedStateHandle = savedStateHandle,
            userRepository = get(),
            sessionRepository = get()
        )
    }
}