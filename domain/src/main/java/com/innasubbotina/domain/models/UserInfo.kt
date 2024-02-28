package com.innasubbotina.domain.models

import java.io.Serializable

data class UserInfo (
    val id : String,
    val avatarUrl : String,
    val firstName : String,
    val lastName : String,
    val userTag : String,
    val department : String,
    val position : String,
    val birthday : String,
    val phone : String,
    var dobShow : Int = 1
) : Serializable
