package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class Inspection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("inspectionType")
    val inspectionType: List<InspectionType>,
    @SerializedName("area")
    val area: List<Area>,
    @SerializedName("survey")
    val survey: List<Survey>,
)
