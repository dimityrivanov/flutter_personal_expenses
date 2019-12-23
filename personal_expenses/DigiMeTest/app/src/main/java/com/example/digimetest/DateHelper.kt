package com.example.digimetest

import android.text.format.DateFormat
import java.util.*

object DateHelper {

    private const val MONTH_IDENTIFIER = "MM"
    private const val MINUS_ONE_MONTH = -1

    fun getDate(time: Long): Date {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time
        val date = Date()
        date.time = time
        return date
    }

    fun getCurrentDate(): Date {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        val date = Date()
        date.time = cal.timeInMillis

        return date
    }

    fun getCurrentMonthString(): String {
        return "${DateFormat.format(MONTH_IDENTIFIER, getCurrentDate())}"
    }

    fun getMonthStringFromMillis(time: Long): String {
        return "${DateFormat.format(MONTH_IDENTIFIER, getDate(time))}"
    }

    fun getPreviousMonthString(): String {
        return "${DateFormat.format(MONTH_IDENTIFIER, getPreviousMonth())}"
    }

    fun getPreviousMonth(): Date {
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.add(Calendar.MONTH, MINUS_ONE_MONTH)
        val date = Date()
        date.time = cal.timeInMillis
        return date
    }
}