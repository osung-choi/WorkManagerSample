package com.osung.worksample.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.*
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.repository.work.EmployeeWorker
import com.osung.worksample.repository.work.InsertWorker
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

        val employeeWorker = OneTimeWorkRequestBuilder<EmployeeWorker>()
            .setInputData(workDataOf(USER_NAME to "오케스트라"))
            .build()

        val insertWorker = OneTimeWorkRequestBuilder<InsertWorker>()
            .build()

        val continuation = workManager.beginWith(employeeWorker)

        continuation.then(insertWorker)
            .enqueue()
    }
}