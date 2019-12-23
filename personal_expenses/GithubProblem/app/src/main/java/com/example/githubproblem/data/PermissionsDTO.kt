package com.example.githubproblem.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PermissionsDTO(

    @field:SerializedName("pull")
    val pull: Boolean? = null,

    @field:SerializedName("admin")
    val admin: Boolean? = null,

    @field:SerializedName("push")
    val push: Boolean? = null
)