package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.local.dao.AuthenticationDao
import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.domain.Repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val authenticationDao: AuthenticationDao
) : RegisterRepository{
    override fun registerUser(credentials: AuthenticationEntity): Flow<AuthenticationEntity> {
        return flow {
            //authenticationDao.insert(credentials)
            //emit(authenticationDao.findAll())
            emit(networkService.registerUser(credentials))
        }.map {
            //it[0]
            it
        }
    }
}