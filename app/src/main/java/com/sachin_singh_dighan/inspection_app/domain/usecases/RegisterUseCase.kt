package com.sachin_singh_dighan.inspection_app.domain.usecases

import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.domain.Repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository){

    operator fun invoke(credentials: AuthenticationEntity): Flow<AuthenticationEntity>{
        return registerRepository.registerUser(credentials)
    }
}