package com.osung.worksample.repository.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.repository.database.AppDatabase
import com.osung.worksample.repository.database.UserDao
import com.osung.worksample.repository.database.entity.UserEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//https://developer.android.com/reference/androidx/hilt/work/HiltWorker
//https://charlezz.medium.com/assistedinject란-무엇인가-35fbc90069a1

@HiltWorker
class EmployeeWorker @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork() = withContext(Dispatchers.IO) {
        try {
            val userName = inputData.getString(USER_NAME)

            if (userName.isNullOrEmpty()) {
                Result.failure()
            } else {
//                val database = AppDatabase.getInstance(applicationContext)
//                database.userDao().insert(UserEntity(0, userName, 23, "010-0000-0000"))
                repository.addUser(
                    UserEntity(0, userName, 23, "010-0000-0000")
                )
            }

            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val USER_NAME = "USER_NAME"
    }
}