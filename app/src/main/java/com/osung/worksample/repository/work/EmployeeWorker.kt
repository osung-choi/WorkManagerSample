package com.osung.worksample.repository.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.util.USER_AGE
import com.osung.worksample.util.USER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

        delay(3000)

        try {
            val userName = inputData.getString(USER_NAME)

            if (userName.isNullOrEmpty()) {
                Result.failure()
            } else {
                Result.success(workDataOf(
                    USER_NAME to userName,
                    USER_AGE to 23)
                )
            }

        } catch (exception: Exception) {
            Result.failure()
        }
    }
}