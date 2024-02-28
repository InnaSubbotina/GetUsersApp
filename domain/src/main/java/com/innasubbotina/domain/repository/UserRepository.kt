package com.innasubbotina.domain.repository

import com.innasubbotina.domain.models.UserInfo

interface UserRepository {

    suspend fun getAllUsers() : Result<List<UserInfo>>

    suspend fun setFilterTab(tabName: String) : Result<List<UserInfo>>

    suspend fun searchUser(text: String) : List<UserInfo>

    suspend fun sortUserByABC(tabName: String) : List<UserInfo>

    suspend fun sortUserByDOB(tabName: String) : List<UserInfo>

}