package com.sachin_singh_dighan.inspection_app.data.local.entity

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class AreaEntity(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
) : EmbeddedRealmObject {
    constructor() : this(
        id = 0,
        name = "",
    )
}
