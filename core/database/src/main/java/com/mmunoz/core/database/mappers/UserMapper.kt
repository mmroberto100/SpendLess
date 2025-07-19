package com.mmunoz.core.database.mappers

import com.mmunoz.core.database.entity.UserEntity
import com.mmunoz.core.database.security.Crypto
import com.mmunoz.core.domain.entity.User
import android.util.Base64
import com.mmunoz.core.domain.entity.BiometricsPrompt
import com.mmunoz.core.domain.entity.Currency
import com.mmunoz.core.domain.entity.DecimalSeparator
import com.mmunoz.core.domain.entity.ExpensesFormat
import com.mmunoz.core.domain.entity.LockedOutDuration
import com.mmunoz.core.domain.entity.SessionExpiryDuration
import com.mmunoz.core.domain.entity.ThousandsSeparator
import com.mmunoz.core.domain.entity.UserSettings


fun User.toEntity(): UserEntity {
    val encryptedPin = Crypto.encrypt(this.pin.toByteArray())
    val encryptedPinBase64 = Base64.encodeToString(encryptedPin, Base64.DEFAULT)

    return UserEntity(
        id = id,
        username = username,
        pin = encryptedPinBase64,
        expensesFormat = settings.expensesFormat.name,
        currency = settings.currency.name,
        decimalSeparator = settings.decimalSeparator.name,
        thousandsSeparator = this.settings.thousandsSeparator.name,
        biometricsPrompt = this.settings.biometricsPrompt.name,
        sessionExpiryDuration = this.settings.sessionExpiryDuration.name,
        lockedOutDuration = this.settings.lockedOutDuration.name
    )
}

fun UserEntity.toDomain(): User {
    val decryptedPinBytes = Crypto.decrypt(Base64.decode(this.pin, Base64.DEFAULT))
    val decryptedPin = String(decryptedPinBytes)
    return User(
        id = id,
        username = username,
        pin = decryptedPin,
        settings = UserSettings(
            expensesFormat = ExpensesFormat.valueOf(expensesFormat),
            currency = Currency.valueOf(currency),
            decimalSeparator = DecimalSeparator.valueOf(currency),
            thousandsSeparator = ThousandsSeparator.valueOf(thousandsSeparator),
            biometricsPrompt = BiometricsPrompt.valueOf(biometricsPrompt),
            sessionExpiryDuration = SessionExpiryDuration.valueOf(sessionExpiryDuration),
            lockedOutDuration = LockedOutDuration.valueOf(lockedOutDuration)

        )
    )
}