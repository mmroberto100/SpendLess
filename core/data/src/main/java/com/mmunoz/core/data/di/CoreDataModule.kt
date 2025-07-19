package com.mmunoz.core.data.di

import android.content.Context
import android.content.SharedPreferences
import com.mmunoz.core.data.repository.SessionRepositoryImpl
import com.mmunoz.core.domain.repository.SessionRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDataModule = module {
    single { provideSharedPreferences(androidContext()) }
    single<SessionRepository> { SessionRepositoryImpl(get()) }
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
}