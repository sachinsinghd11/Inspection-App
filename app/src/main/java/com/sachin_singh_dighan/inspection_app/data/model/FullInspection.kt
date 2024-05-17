package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class FullInspection(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)
