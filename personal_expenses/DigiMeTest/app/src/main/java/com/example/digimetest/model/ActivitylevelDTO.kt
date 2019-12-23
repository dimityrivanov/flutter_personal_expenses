package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class ActivitylevelDTO(

	@field:SerializedName("minutes")
	val minutes: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)