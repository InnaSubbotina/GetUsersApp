package com.innasubbotina.usersapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.FragmentNotFoundBinding

class NotFoundFragment : Fragment() {

    private lateinit var binding: FragmentNotFoundBinding
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentNotFoundBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tryAgainListener()
    }

    private fun tryAgainListener(){
        binding.criticalErrorText.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

}