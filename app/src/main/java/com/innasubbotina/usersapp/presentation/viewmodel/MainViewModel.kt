package com.innasubbotina.usersapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.domain.usecase.FilterByDepartments
import com.innasubbotina.domain.usecase.GetAllUsers
import com.innasubbotina.domain.usecase.SearchUser
import com.innasubbotina.domain.usecase.SortUsers
import kotlinx.coroutines.delay


class MainViewModel(private val getAllUsers : GetAllUsers,
                    private val filterByDeps: FilterByDepartments,
                    private val searchUser: SearchUser,
                    private val sortUsers: SortUsers
) : ViewModel() {

    val userLiveData = MutableLiveData<List<UserInfo>>()
    //val userFilterLiveData = MutableLiveData<List<UserInfo>>()
    //val searchUserList = MutableLiveData<List<UserInfo>>()
    //private val searchText = MutableLiveData<String>()

    suspend fun loadAllUsers(): Result<List<UserInfo>> {
        delay(500) //чтобы показать работу shimmer layout
        val users = getAllUsers.executeGetAllUsers()
        val usersInfo = users.getOrNull()
        userLiveData.postValue(usersInfo!!)
        return users
    }

    suspend fun filterByDepartment(tabName: String): Result<List<UserInfo>> {
        val filterUser = filterByDeps.executeFilterByDeps(tabName)
        //val filterUserInfo = filterUser.getOrNull()
        //userFilterLiveData.postValue(filterUserInfo!!)
        return filterUser
    }

    suspend fun searchUser(text: String): List<UserInfo> {
        return searchUser.executeSearchUser(text)
    }

    /* suspend fun sortUsersByABC(userList: List<UserInfo>)  {
        return sortUsers.executeSortUsersByABC(userList)
    }*/

    suspend fun sortUsersByABC(tabName: String): List<UserInfo> {
        return sortUsers.executeSortUsersByABC(tabName)
    }

    suspend fun sortUsersByDOB(tabName: String): List<UserInfo> {
        return sortUsers.executeSortUsersByDOB(tabName)
    }
}