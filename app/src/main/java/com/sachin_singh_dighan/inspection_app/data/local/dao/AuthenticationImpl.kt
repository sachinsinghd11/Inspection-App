package com.sachin_singh_dighan.inspection_app.data.local.dao

import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

class AuthenticationImpl @Inject constructor(realm: Realm): AuthenticationDao {
    override val realm: Realm = realm
    override val clazz: KClass<AuthenticationEntity> = AuthenticationEntity::class

}