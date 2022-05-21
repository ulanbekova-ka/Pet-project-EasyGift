package com.kay.prog.easygift.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
fun countDaysLeft(date: String): Int {
    val format = "dd/MM/yyyy"
    val fromDate = SimpleDateFormat(format).parse(date)

    val millionSeconds = fromDate!!.time - Calendar.getInstance().timeInMillis
    val days = TimeUnit.MILLISECONDS.toDays(millionSeconds) % 365 + 365
    return days.toInt()
}