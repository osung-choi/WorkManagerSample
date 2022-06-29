package com.osung.workersample.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.osung.workersample.repository.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query("select * from user")
    fun select(): Flow<List<UserEntity>>
}