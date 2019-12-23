package com.example.githubproblem.helper

import retrofit2.Call
//This method catches all the errors from networking operations
object SafeRequestExecutor {
    fun <T> execute(call: Call<T>): Any? {
        return try {
            call.execute().body()
        } catch (ex: Exception) {
            //logging can be added to catch all exceptions
            null
        }
    }
}