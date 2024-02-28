package com.innasubbotina.usersapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.innasubbotina.domain.models.UserInfo
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.FragmentUserDetailsBinding
import com.innasubbotina.usersapp.presentation.extension.formatNumberPhone
import com.innasubbotina.usersapp.presentation.extension.toLocalDate
import com.innasubbotina.usersapp.presentation.extension.userAge
import com.innasubbotina.usersapp.presentation.viewmodel.MainViewModel
import com.innasubbotina.usersapp.presentation.viewmodel.MainViewModelFactory

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private var user: UserInfo? = null
    private lateinit var viewModel: MainViewModel
    val navController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getSerializable("user") as UserInfo
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(requireContext())
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentUserDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
        binding.buttonBack.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

    private fun setUserData() = with(binding) {
        val name = user?.firstName
        val lastName = user?.lastName
        val fullName = "$name $lastName"
        textViewName.text = fullName
        imViewUser.setImageResource(R.drawable.gus)
        tvPhone.text = user?.phone?.formatNumberPhone()
        textViewDept.text = user?.department
        tviewDOB.text = user?.birthday?.toLocalDate(1)
        textViewAge.text = userAge(user?.birthday.toString(),requireContext())
    }

}