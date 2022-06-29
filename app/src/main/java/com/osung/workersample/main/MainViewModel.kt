package com.osung.workersample.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osung.workersample.repository.UserRepository
import com.osung.workersample.repository.database.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private var index = 0
    val userList = userRepository.getUserList()

    fun addNewEmployee() {
        viewModelScope.launch {
            val user = UserEntity(0, "신규입사자 ${index++}", 23, "010-0000-0000")
            userRepository.addUser(user)
        }
    }
}