package com.sachin_singh_dighan.inspection_app.domain.usecases

import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.domain.Repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(credentials: AuthenticationEntity): Flow<AuthenticationEntity>{
        return loginRepository.loginUser(credentials)
    }

}