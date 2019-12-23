package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class DurationsDTO(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("original")
	val original: Int? = null,

	@field:SerializedName("active")
	val active: Int? = null
)