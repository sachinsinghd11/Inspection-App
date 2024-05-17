package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)
