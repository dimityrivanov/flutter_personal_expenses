package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class ManualvaluesspecifiedDTO(

    @field:SerializedName("distance")
    val distance: Boolean? = null,

    @field:SerializedName("calories")
    val calories: Boolean? = null,

    @field:SerializedName("steps")
    val steps: Boolean? = null
)