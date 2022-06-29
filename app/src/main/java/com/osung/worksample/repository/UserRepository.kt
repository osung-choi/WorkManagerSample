package com.osung.worksample.repository

import com.osung.worksample.repository.database.UserDao
import com.osung.worksample.repository.database.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getUserList() = userDao.select()

    suspend fun addUser(user: UserEntity) {
        userDao.insert(user)
    }
}