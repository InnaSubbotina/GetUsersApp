package com.innasubbotina.domain.usecase

import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.repository.UserRepository

class SearchUser(private val userRepository: UserRepository) {

    suspend fun executeSearchUser(text: String) : List<UserInfo> {
        return userRepository.searchUser(text)
    }
}