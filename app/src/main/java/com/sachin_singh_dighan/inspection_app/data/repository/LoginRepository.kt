package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.model.Authentication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val networkService: NetworkService) {

    fun loginUser(credentials: Authentication): Flow<Authentication> {
        return flow {
            emit(networkService.loginUser(credentials))
        }.map {
            it
        }
    }
}