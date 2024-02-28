package com.innasubbotina.data.repository

import android.content.Context
import com.innasubbotina.data.R
import com.innasubbotina.data.mapper.departments
import com.innasubbotina.data.mapper.toUserInfo
import com.innasubbotina.data.mapper.toUserInfoDOB
import com.innasubbotina.data.network.UserApi
import com.innasubbotina.data.network.models.User
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.repository.UserRepository
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val context: Context,
) : UserRepository {

    private var cachingUsers: List<User> = emptyList()
    private var userInfo: List<UserInfo> = emptyList()
    private var filterUser: List<UserInfo> = emptyList()
    private var sortedList: List<UserInfo> = emptyList()
    private var tabName: String = ALL

    override suspend fun getAllUsers(): Result<List<UserInfo>> {
        try {
            val users = userApi.getUsers()
            cachingUsers = users.items
        } catch (e: IOException) {
            return Result.failure(Exception(context.getString(R.string.network_error)))
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.success(cachingUsers.map { it.toUserInfo() })
    }

    override suspend fun setFilterTab(tabName: String): Result<List<UserInfo>> {
        this.tabName = tabName
        return if (tabName == ALL) {
            getAllUsers()
        } else {
            val users = userApi.getUsers()
            cachingUsers = users.items
            val userItems = cachingUsers.map { it.toUserInfo() }
            val resultUserItem = Result.success(userItems.filter { it.department == departments[tabName] })
            return resultUserItem
        }
    }

    override suspend fun sortUserByABC(tabName: String) : List<UserInfo> {
        val users = userApi.getUsers()
        userInfo = users.items.map { it.toUserInfo() }
        sortedList = if (tabName == ALL) {
            val sortedList = userInfo.sortedBy { it.firstName }
            sortedList
        } else {
            val filterUser = userInfo.filter { it.department == departments[tabName] }
            val sortedList = filterUser.sortedBy { it.firstName  }
            sortedList
        }
        return sortedList
    }

    override suspend fun sortUserByDOB(tabName: String): List<UserInfo> {
        val users = userApi.getUsers()
        userInfo = users.items.map { it.toUserInfoDOB() }
        return if (tabName == ALL) {
            sortByDOB(userInfo)
        } else {
            filterUser = userInfo.filter { it.department == departments[tabName] }
            sortByDOB(filterUser)
        }
    }

    private fun sortByDOB(list: List<UserInfo>) : List<UserInfo> {
        val now = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var listFuture = list.filter {
            val birthday = LocalDate.parse(it.birthday, formatter)
            val date = birthday.withYear(now.year)
            date.isAfter(now)
        }
        listFuture = listFuture.sortedBy { LocalDate.parse(it.birthday, formatter) }
        var listLast = list.filter {
            val birthday = LocalDate.parse(it.birthday, formatter)
            val date = birthday.withYear(now.year)
            date.isBefore(now)
        }
        listLast = listLast.sortedBy { LocalDate.parse(it.birthday, formatter) }
        val sortedListDOB = mutableListOf<UserInfo>()
        sortedListDOB.addAll(listFuture)
        sortedListDOB += listLast
        return sortedListDOB
    }

    override suspend fun searchUser(text: String): List<UserInfo> {
        val searchList = mutableSetOf<UserInfo>()
        val users = userApi.getUsers()
        userInfo = users.items.map { it.toUserInfo() }
        for(user in userInfo) {
            if (user.firstName.toLowerCase().trim().contains(text) ||
                user.lastName.toLowerCase().trim().contains(text)) {
                searchList.add(user)
            }
        }
        return searchList.toList()
    }

        companion object {
            const val ALL = "Все"
        }

}

