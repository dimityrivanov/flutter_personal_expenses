package com.example.githubproblem.helper

import java.text.SimpleDateFormat

object AppUtils {
    var DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    fun getDate(dateString: String): String {

        try {
            val format1 = SimpleDateFormat(DATE_TIME_FORMAT)
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("MMM d, yyyy")
            return sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "xx"
        }

    }

    fun getTime(dateString: String): String {

        try {
            val format1 = SimpleDateFormat(DATE_TIME_FORMAT)
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("h:mm a")
            return sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "xx"
        }

    }
}