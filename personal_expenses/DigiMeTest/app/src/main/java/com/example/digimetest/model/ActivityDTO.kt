package com.example.digimetest.model

import com.google.gson.annotations.SerializedName

data class ActivityDTO(

    @field:SerializedName("distance")
	val distance: Double? = null,

    @field:SerializedName("createddate")
	val createddate: Long? = null,

    @field:SerializedName("averageheartrate")
	val averageheartrate: Double? = null,

    @field:SerializedName("entityid")
	val entityid: String? = null,

    @field:SerializedName("calories")
	val calories: Double? = null,

    @field:SerializedName("steps")
	val steps: Double? = null,

    @field:SerializedName("accountentityid")
	val accountentityid: String? = null,

    @field:SerializedName("speed")
	val speed: Double? = null,

    @field:SerializedName("activitytypeid")
	val activitytypeid: String? = null,

    @field:SerializedName("logtype")
	val logtype: String? = null,

    @field:SerializedName("originalstartdate")
	val originalstartdate: Long? = null,

    @field:SerializedName("elevationgain")
	val elevationgain: Double? = null,

    @field:SerializedName("updateddate")
	val updateddate: Long? = null,

    @field:SerializedName("durations")
	val durations: DurationsDTO? = null,

    @field:SerializedName("activitylevel")
	val activitylevel: List<ActivitylevelDTO?>? = null,

    @field:SerializedName("activityname")
	val activityname: String? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("manualvaluesspecified")
	val manualvaluesspecified: ManualvaluesspecifiedDTO? = null
)