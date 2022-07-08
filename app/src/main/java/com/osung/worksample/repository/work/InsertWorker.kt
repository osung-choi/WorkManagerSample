package com.osung.worksample.repository.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.repository.database.entity.UserEntity
import com.osung.worksample.util.USER_AGE
import com.osung.worksample.util.USER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@HiltWorker
class InsertWorker @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork() = withContext(Dispatchers.IO) {
        delay(3000)

        try {
            val userName = inputData.getString(USER_NAME)
            val userAge = inputData.getInt(USER_AGE, 0)

            if (userName.isNullOrEmpty() || userAge == 0) {
                Result.failure()
            } else {
                repository.addUser(
                    UserEntity(0, userName, userAge, "010-0000-0000")
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val USER_ENTITY = "USER_ENTITY"
    }
}