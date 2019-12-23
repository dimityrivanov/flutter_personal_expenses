package com.example.githubproblem.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerDTO(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null
)