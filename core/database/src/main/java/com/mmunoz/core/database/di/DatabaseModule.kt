package com.mmunoz.core.database.di

import androidx.room.Room
import com.mmunoz.core.database.SpendLessDatabase
import com.mmunoz.core.database.repository.UserRepositoryImpl
import com.mmunoz.core.domain.repository.UserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendLessDatabase::class.java,
            "spend_less.db"
        ).build()
    }

    single{ get<SpendLessDatabase>().userDao()}

    single<UserRepository> { UserRepositoryImpl(get()) }
}