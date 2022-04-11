package com.jcardenas.domain.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"

    fun getCalendar() : Calendar = Calendar.getInstance()

    fun getDateFormat(format: String): SimpleDateFormat {
        return SimpleDateFormat(format, Locale.getDefault())
    }

    fun getDateByFormat(format: String): String{
        val calendar = getCalendar()
        val dateFormat = getDateFormat(format)
        return dateFormat.format(calendar.time)
    }
}