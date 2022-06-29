package com.osung.workersample.repository

import android.util.Log
import com.osung.workersample.repository.database.UserDao
import com.osung.workersample.repository.database.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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