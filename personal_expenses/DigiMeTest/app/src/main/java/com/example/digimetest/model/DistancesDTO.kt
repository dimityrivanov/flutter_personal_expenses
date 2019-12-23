package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class DistancesDTO(

	@field:SerializedName("activity")
	val activity: String? = null,

	@field:SerializedName("distance")
	val distance: Double? = null
)