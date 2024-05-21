package com.sachin_singh_dighan.inspection_app.data.local.entity

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class AnswerChoiceEntity(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("score")
    var score: Double = 0.0,
) : EmbeddedRealmObject {
    constructor() : this(
        id = 0,
        name = "",
        score = 0.0

    )
}