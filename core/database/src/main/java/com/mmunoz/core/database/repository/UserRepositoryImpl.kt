package com.mmunoz.core.database.repository

import com.mmunoz.core.database.dao.UserDao
import com.mmunoz.core.database.mappers.toDomain
import com.mmunoz.core.database.mappers.toEntity
import com.mmunoz.core.domain.entity.User
import com.mmunoz.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
): UserRepository {

    override suspend fun upsertUser(user: User) {
        userDao.upsertUser(user.toEntity())
    }

    override suspend fun getUser(username: String): User? {
        return userDao.getUser(username)?.toDomain()
    }

    override fun getFlowUser(username: String): Flow<User?> {
        return userDao.getFlowUser(username).map {
            it?.toDomain()
        }
    }
}