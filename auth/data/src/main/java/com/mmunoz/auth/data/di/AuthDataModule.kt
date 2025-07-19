package com.mmunoz.auth.data.di

import com.mmunoz.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::UserDataValidator)
}