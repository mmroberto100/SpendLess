package com.mmunoz.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmunoz.core.domain.repository.SessionRepository
import com.mmunoz.core.presentation.designsystem.theme.SpendLessTheme
import com.mmunoz.spendless.navigation.NavigationRoot
import com.mmunoz.spendless.navigation.rememberNavigationState
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val sessionRepository: SessionRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                val navigationState = rememberNavigationState()
                NavigationRoot(
                    navigationState = navigationState,
                    sessionRepository = sessionRepository
                )
            }
        }
    }
}