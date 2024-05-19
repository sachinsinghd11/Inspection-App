package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class Inspection(
    @SerializedName("error")
    val error: Error? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("inspectionType")
    val inspectionType: InspectionType,
    @SerializedName("area")
    val area: Area,
    @SerializedName("survey")
    val survey: Survey,
)
