package com.osung.worksample.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.repository.work.EmployeeWorker
import com.osung.worksample.util.USER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
): AndroidViewModel(application) {
    private val workManager = WorkManager.getInstance(application)

    val userList = userRepository.getUserList()

    fun addNewEmployee() {
        val workRequest = OneTimeWorkRequestBuilder<EmployeeWorker>()
            .setInputData(workDataOf(USER_NAME to "신규입사자"))
            .build()

        workManager.enqueue(workRequest)
    }
}