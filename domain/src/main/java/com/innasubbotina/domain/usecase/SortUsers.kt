package com.innasubbotina.domain.usecase

import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.repository.UserRepository

class SortUsers(private val userRepository : UserRepository) {

   suspend fun executeSortUsersByABC(tabName: String) : List<UserInfo>  {
        return userRepository.sortUserByABC(tabName)
    }

    suspend fun executeSortUsersByDOB(tabName: String) : List<UserInfo>  {
        return userRepository.sortUserByDOB(tabName)
    }

}