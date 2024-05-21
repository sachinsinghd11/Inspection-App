package com.sachin_singh_dighan.inspection_app.data.local.dao

import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import com.sachin_singh_dighan.inspection_app.data.model.AnswerChoice
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.reflect.KClass

class InspectionDaoImpl @Inject constructor( realm: Realm)  : InspectionDao {

    override val realm: Realm = realm
    override val clazz: KClass<InspectionEntity> = InspectionEntity::class

}