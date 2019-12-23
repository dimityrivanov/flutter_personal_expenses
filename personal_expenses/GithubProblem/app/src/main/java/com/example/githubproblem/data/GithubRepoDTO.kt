package com.example.githubproblem.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubRepoDTO(

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("owner")
    val owner: OwnerDTO? = null

)