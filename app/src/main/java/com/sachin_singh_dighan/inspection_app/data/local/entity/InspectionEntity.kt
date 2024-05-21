package com.sachin_singh_dighan.inspection_app.data.local.entity

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class InspectionEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("inspectionType")
    var inspectionType: InspectionTypeEntity?,
    @SerializedName("area")
    var area: AreaEntity?,
    @SerializedName("survey")
    var survey: SurveyEntity?,
) : RealmObject {
    constructor() : this(
        id = 0,
        inspectionType = InspectionTypeEntity(),
        area = AreaEntity(),
        survey = SurveyEntity()

    )
}
