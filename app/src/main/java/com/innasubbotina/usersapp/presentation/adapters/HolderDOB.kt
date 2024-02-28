package com.innasubbotina.usersapp.presentation.adapters

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.UserListItemDobBinding
import com.innasubbotina.usersapp.presentation.extension.toLocalDate
import com.innasubbotina.usersapp.presentation.ui.OnClickUserDetails

class HolderDOB(private val binding: UserListItemDobBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindData(user: UserInfo, listener: OnClickUserDetails) = with(binding) {
        val name = "${user.firstName} ${user.lastName}"
        imViewUser.setImageResource(R.drawable.gus)
        tvName.text = name
        tvDepartment.text = user.department
        tvBirthday.text = user.birthday.toLocalDate(2)
        itemView.setOnClickListener {
            val bungleUser = Bundle()
            bungleUser.putSerializable("user",user)
            listener.onClickUserDetails(bungleUser)
        }
    }
}