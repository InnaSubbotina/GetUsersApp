package com.innasubbotina.usersapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.innasubbotina.data.network.NetworkUserApi
import com.innasubbotina.data.repository.UserRepositoryImpl
import com.innasubbotina.domain.usecase.FilterByDepartments
import com.innasubbotina.domain.usecase.GetAllUsers
import com.innasubbotina.domain.usecase.SearchUser
import com.innasubbotina.domain.usecase.SortUsers

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy {
       UserRepositoryImpl (userApi = NetworkUserApi(context = context),context)
    }
    private val getAllUsers by lazy {
        GetAllUsers (userRepository = userRepository)
    }
    private val filterByDeps by lazy {
        FilterByDepartments (userRepository = userRepository)
    }
    private val searchUser by lazy {
        SearchUser (userRepository = userRepository)
    }
    private val sortUsers by lazy {
        SortUsers (userRepository = userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getAllUsers = getAllUsers,
            filterByDeps = filterByDeps,
            searchUser = searchUser,
            sortUsers = sortUsers
        ) as T
    }




}