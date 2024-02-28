package com.innasubbotina.data.mapper

import com.innasubbotina.data.network.models.User
import com.innasubbotina.domain.models.UserInfo

fun User.toUserInfo() : UserInfo {
    return UserInfo(
        id = id,
        avatarUrl = avatarUrl,
        firstName = firstName,
        lastName = lastName,
        userTag = userTag.toLowerCase(),
        department = department,
        position = position,
        birthday = birthday,
        phone = phone,
        dobShow = 1
    )
}

fun User.toUserInfoDOB() : UserInfo {
    return UserInfo(
        id = id,
        avatarUrl = avatarUrl,
        firstName = firstName,
        lastName = lastName,
        userTag = userTag.toLowerCase(),
        department = department,
        position = position,
        birthday = birthday,
        phone = phone,
        dobShow = 2
    )
}
