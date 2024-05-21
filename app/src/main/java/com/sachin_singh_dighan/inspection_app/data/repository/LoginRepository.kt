package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.local.dao.AuthenticationDao
import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val networkService: NetworkService, private val authenticationDao: AuthenticationDao
) {

    fun loginUser(credentials: AuthenticationEntity): Flow<AuthenticationEntity> {
        return flow {
            //emit(authenticationDao.findById(credentials.email, credentials.password))
            emit(networkService.loginUser(credentials))
        }.map {
           // return@map it ?: AuthenticationEntity()
            it
        }
    }
}