package com.innasubbotina.data.network.models

import java.time.LocalDate

data class User (
    val id : String,
    val avatarUrl : String,
    val firstName : String,
    val lastName : String,
    val userTag : String,
    val department : String,
    val position : String,
    val birthday : String,
    val phone : String,
    var dobShow : Int
)
