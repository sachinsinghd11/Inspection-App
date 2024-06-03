package com.sachin_singh_dighan.inspection_app.domain.Repository

import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun registerUser(credentials: AuthenticationEntity): Flow<AuthenticationEntity>
}