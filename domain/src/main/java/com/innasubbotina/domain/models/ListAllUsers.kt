package com.innasubbotina.domain.models

import java.io.Serializable

data class ListAllUsers (
    val items: List<UserInfo>
)  : Serializable
