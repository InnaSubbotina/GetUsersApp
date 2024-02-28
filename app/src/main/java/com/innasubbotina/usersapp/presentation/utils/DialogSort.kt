package com.innasubbotina.usersapp.presentation.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.DialogSortBinding

object DialogSort {

    fun showDialog(context: Context, callback: RadioButtonListener) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DialogSortBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
           groupRadioButtons.setOnCheckedChangeListener { _, checkedId ->
                        when (checkedId) {
                            R.id.radiobutton_toAlf ->
                                callback.onClickSortABC()
                            R.id.radiobuttonToBirthday ->
                                callback.onClickSortDOB()
                            else -> throw RuntimeException("Illegal checkedId in Radio Buttons")
                        }
                   dialog?.dismiss()
                }
            }
        dialog = builder.create()
        dialog.show()
    }

}





