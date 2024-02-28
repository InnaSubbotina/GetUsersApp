package com.innasubbotina.usersapp.presentation.extension

import android.content.Context
import com.innasubbotina.usersapp.R
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toLocalDate(dateType: Int): String? {
    var result = ""
    val oldFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.getDefault())
    val date = LocalDate.parse(this,oldFormatter)
    result = if(dateType == 1) {
           val newFormatter = DateTimeFormatter.ofPattern("d MMM yyyy",Locale("ru"))
           result = LocalDate.of(date.year,date.month,date.dayOfMonth).format(newFormatter)
           result
       } else {
           val newFormatter = DateTimeFormatter.ofPattern("d MMM",Locale("ru"))
           result = LocalDate.of(date.year,date.month,date.dayOfMonth).format(newFormatter)
           result
       }
        return result
}

fun String.formatNumberPhone(): String? {
    return try {
        "+7 (${this.substring(0,3)}) ${this.substring(4,7)} ${this.substring(8,10)} ${this.substring(10,this.length)}"
    } catch (e: java.lang.Exception){
        null
    }
}

fun userAge(date: String?, context: Context) : String {
    val oldFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.getDefault())
    val dob = LocalDate.parse(date,oldFormatter)
    val today = LocalDate.now()
    val period = Period.between(dob,today).years
    return when {
        period % 100 in 11..14 -> "$period ${context.getString(R.string.years1)}"
        period % 10 == 1 -> "$period ${context.getString(R.string.years2)}"
        period % 10 in 2..4 -> "$period ${context.getString(R.string.years2)}"
        else -> "$period ${context.getString(R.string.years1)}"
    }
}

