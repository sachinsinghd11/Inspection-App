package com.sachin_singh_dighan.inspection_app.data.local.entity

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey

open class CategoryEntity(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("questions")
    var questions: RealmList<QuestionEntity>,
) : EmbeddedRealmObject {
    constructor() : this(
        id = 0,
        name = "",
        questions = realmListOf<QuestionEntity>()
    )
}
