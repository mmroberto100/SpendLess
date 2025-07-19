package com.mmunoz.core.presentation.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import com.mmunoz.core.presentation.designsystem.theme.SpendLessBlackPurple
import com.mmunoz.core.presentation.designsystem.theme.SpendLessBlackSoft
import com.mmunoz.core.presentation.designsystem.theme.SpendLessGrayDark
import com.mmunoz.core.presentation.designsystem.theme.SpendLessGrayDarker
import com.mmunoz.core.presentation.designsystem.theme.SpendLessGrayMedium
import com.mmunoz.core.presentation.designsystem.theme.SpendLessLavender
import com.mmunoz.core.presentation.designsystem.theme.SpendLessLightBlue
import com.mmunoz.core.presentation.designsystem.theme.SpendLessLimeGreen
import com.mmunoz.core.presentation.designsystem.theme.SpendLessOliveDark
import com.mmunoz.core.presentation.designsystem.theme.SpendLessOliveDarker
import com.mmunoz.core.presentation.designsystem.theme.SpendLessPurpleBright
import com.mmunoz.core.presentation.designsystem.theme.SpendLessPurpleDark
import com.mmunoz.core.presentation.designsystem.theme.SpendLessRedDark
import com.mmunoz.core.presentation.designsystem.theme.SpendLessWhite
import com.mmunoz.core.presentation.designsystem.theme.SpendLessWhiteGrayish
import com.mmunoz.core.presentation.designsystem.theme.SpendLessWhiteLight
import com.mmunoz.core.presentation.designsystem.theme.SpendLessWhitePinkish
import com.mmunoz.core.presentation.designsystem.theme.SpendLessWhiteSoft
import com.mmunoz.core.presentation.designsystem.theme.Typography

val LightColorScheme = lightColorScheme(
    primary = SpendLessPurpleDark,
    onPrimary = SpendLessWhite,
    primaryContainer = SpendLessPurpleBright,
    inversePrimary = SpendLessLavender,
    secondary = SpendLessOliveDark,
    secondaryContainer = SpendLessLimeGreen,
    onSecondaryContainer = SpendLessOliveDarker,
    tertiaryContainer = SpendLessLightBlue,
    background = SpendLessWhitePinkish,
    onBackground = SpendLessBlackPurple,
    surface = SpendLessWhiteSoft,
    surfaceContainerLow = SpendLessWhiteGrayish,
    onSurface = SpendLessBlackSoft,
    onSurfaceVariant = SpendLessGrayDark,
    outline = SpendLessGrayMedium,
    inverseSurface = SpendLessGrayDarker,
    inverseOnSurface = SpendLessWhiteLight,
    error = SpendLessRedDark,
    onError = SpendLessWhite
)

@Composable
fun SpendLessTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}