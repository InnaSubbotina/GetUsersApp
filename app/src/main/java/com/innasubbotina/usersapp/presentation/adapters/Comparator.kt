package com.innasubbotina.usersapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.innasubbotina.domain.models.UserInfo

class Comparator : DiffUtil.ItemCallback<UserInfo>() {
    override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
        return oldItem == newItem
    }
}