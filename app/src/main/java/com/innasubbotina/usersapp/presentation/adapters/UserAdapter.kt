package com.innasubbotina.usersapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.usersapp.databinding.UserListItemBinding
import com.innasubbotina.usersapp.databinding.UserListItemDobBinding
import com.innasubbotina.usersapp.presentation.ui.OnClickUserDetails

class UserAdapter(private val listener: OnClickUserDetails)
    : ListAdapter<UserInfo, RecyclerView.ViewHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> HolderBase(
                UserListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

            2 -> HolderDOB(
                UserListItemDobBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> throw IllegalArgumentException("not arguments")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItem(position).dobShow) {
            1 -> (holder as HolderBase).bindData(getItem(position),listener)
            2 -> (holder as HolderDOB).bindData(getItem(position),listener)
            else -> throw IllegalArgumentException("not arguments")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).dobShow
    }

}