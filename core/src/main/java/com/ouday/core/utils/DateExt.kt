package com.ouday.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar
}

fun Calendar.toReadableDateFormat(): String {
    val format = SimpleDateFormat("MMM dd YY")
    return format.format(this.time)
}

fun Calendar.toReadableTimeFormat(): String {
    val format = SimpleDateFormat("HH:mm")
    return format.format(this.time)
}