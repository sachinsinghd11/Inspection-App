package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class Error(
    val id: Int,
    @SerializedName("error")
    val error: String,
)
