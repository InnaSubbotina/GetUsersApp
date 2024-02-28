package com.innasubbotina.data.network

import com.innasubbotina.data.network.models.ListUsers
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers() : ListUsers
}