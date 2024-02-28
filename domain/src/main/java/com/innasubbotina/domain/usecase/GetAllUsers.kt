package com.innasubbotina.domain.usecase

import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.repository.UserRepository

class GetAllUsers (private val userRepository: UserRepository) {

    suspend fun executeGetAllUsers() : Result<List<UserInfo>> {
        return userRepository.getAllUsers()
    }

}