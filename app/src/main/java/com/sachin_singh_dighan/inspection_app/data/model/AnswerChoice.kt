package com.sachin_singh_dighan.inspection_app.data.model

import com.google.gson.annotations.SerializedName

data class AnswerChoice(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: String,
)
