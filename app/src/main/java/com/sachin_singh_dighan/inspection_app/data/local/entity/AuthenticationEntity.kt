package com.sachin_singh_dighan.inspection_app.data.local.entity

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import java.util.UUID

open class AuthenticationEntity(
    @SerializedName("id")
    var _id: String = UUID.randomUUID().toString(),
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
) : RealmObject {
    constructor() : this(
        _id = "",
        email = "",
        password = "",
    )
}