package com.innasubbotina.usersapp.presentation.adapters

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.UserListItemBinding
import com.innasubbotina.usersapp.presentation.ui.OnClickUserDetails

class HolderBase(private val binding: UserListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bindData(user: UserInfo,listener: OnClickUserDetails) = with(binding) {
        val name = "${user.firstName} ${user.lastName}"
        imViewUser.setImageResource(R.drawable.gus)
        tvName.text = name
        tvDepartment.text = user.department
        itemView.setOnClickListener {
            val bungleUser = Bundle()
            bungleUser.putSerializable("user",user)
            listener.onClickUserDetails(bungleUser)
        }
    }
}
