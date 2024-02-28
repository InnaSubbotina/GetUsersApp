package com.innasubbotina.data.network

import android.content.Context
import com.innasubbotina.data.network.models.ListUsers
import com.innasubbotina.data.network.retrofit.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkUserApi (private val context: Context) : UserApi {

    override suspend fun getUsers(): ListUsers {
        val retrofit = RetrofitBuilder.build()
        val userApi = retrofit.create(UserApi::class.java)
        return withContext(Dispatchers.IO) {
            val users = userApi.getUsers()
            ListUsers(items = users.items)
        }
    }
}


