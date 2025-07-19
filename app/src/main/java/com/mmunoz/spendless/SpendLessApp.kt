package com.mmunoz.spendless

import android.app.Application
import com.mmunoz.auth.data.di.authDataModule
import com.mmunoz.auth.presentation.di.authViewModelModule
import com.mmunoz.core.data.di.coreDataModule
import com.mmunoz.core.database.di.databaseModule
import com.mmunoz.spendless.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpendLessApp : Application() {

    override fun onCreate(){
        super.onCreate()
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }

        startKoin {
            androidLogger()
            androidContext(this@SpendLessApp)
            modules(
                appModule,
                coreDataModule,
                authViewModelModule,
                authDataModule,
                databaseModule
            )
        }
    }
}