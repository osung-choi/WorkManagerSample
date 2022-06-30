package com.osung.worksample.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.osung.worksample.repository.UserRepository
import com.osung.worksample.repository.database.entity.UserEntity
import com.osung.worksample.repository.work.EmployeeWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
): AndroidViewModel(application) {
    private val workRequest = OneTimeWorkRequestBuilder<EmployeeWorker>()
        .addTag(WORK_REQUEST_TAG)
        .build()

    private var index = 0
    //val userList = userRepository.getUserList().asLiveData()
    val userList = WorkManager.getInstance(application).getWorkInfosByTagLiveData(WORK_REQUEST_TAG)

    fun addNewEmployee() {
        viewModelScope.launch {
            val user = UserEntity(0, "신규입사자 ${index++}", 23, "010-0000-0000")
            userRepository.addUser(user)
        }
    }

    companion object {
        const val WORK_REQUEST_TAG = "WORK_REQUEST_TAG"
    }
}