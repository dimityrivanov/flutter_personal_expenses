package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class StepsDTO(

    @field:SerializedName("elevation")
	val elevation: Int? = null,

    @field:SerializedName("distances")
	val distances: List<DistancesDTO?>? = null,

    @field:SerializedName("marginalcalories")
	val marginalcalories: Int? = null,

    @field:SerializedName("activescore")
	val activescore: Int? = null,

    @field:SerializedName("createddate")
	val createddate: Long = 0,

    @field:SerializedName("sedentaryminutes")
	val sedentaryminutes: Int? = null,

    @field:SerializedName("entityid")
	val entityid: String? = null,

    @field:SerializedName("fairlyactiveminutes")
	val fairlyactiveminutes: Int? = null,

    @field:SerializedName("activitycalories")
	val activitycalories: Int? = null,

    @field:SerializedName("restingheartrate")
	val restingheartrate: Int? = null,

    @field:SerializedName("steps")
	val steps: Int? = null,

    @field:SerializedName("accountentityid")
	val accountentityid: String? = null,

    @field:SerializedName("floors")
	val floors: Int? = null,

    @field:SerializedName("veryactiveminutes")
	val veryactiveminutes: Int? = null,

    @field:SerializedName("caloriesout")
	val caloriesout: Int? = null,

    @field:SerializedName("lightlyactiveminutes")
	val lightlyactiveminutes: Int? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("caloriesbmr")
	val caloriesbmr: Int? = null,

    @field:SerializedName("goals")
	val goals: GoalsDTO? = null
)