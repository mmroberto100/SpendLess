package com.mmunoz.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mmunoz.core.database.dao.UserDao
import com.mmunoz.core.database.entity.UserEntity
import com.mmunoz.core.database.mappers.SettingsConverter

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(SettingsConverter::class)
abstract class SpendLessDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}