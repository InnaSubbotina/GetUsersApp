package com.innasubbotina.domain.usecase

import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.repository.UserRepository

class FilterByDepartments (private val userRepository: UserRepository) {

    suspend fun executeFilterByDeps(tabName: String) : Result<List<UserInfo>> {
      return userRepository.setFilterTab(tabName)
    }
}