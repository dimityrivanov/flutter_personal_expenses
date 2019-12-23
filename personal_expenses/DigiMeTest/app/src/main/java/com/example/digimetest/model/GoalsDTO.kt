package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class GoalsDTO(

	@field:SerializedName("floors")
	val floors: Int? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("caloriesout")
	val caloriesout: Int? = null,

	@field:SerializedName("activeminutes")
	val activeminutes: Int? = null,

	@field:SerializedName("steps")
	val steps: Int? = null
)