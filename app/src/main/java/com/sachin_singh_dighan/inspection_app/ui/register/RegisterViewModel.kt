package com.sachin_singh_dighan.inspection_app.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.data.repository.RegisterRepository
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.utils.AppConstant
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerRepository: RegisterRepository,
    private val networkHelper: NetworkHelper,
    private val logger: Logger
) : ViewModel() {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<AuthenticationEntity>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<AuthenticationEntity>> = _uiState


    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            if (networkHelper.isNetworkAvailable()) {
                registerRepository.registerUser(generateAuthenticationObject(email, password))
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        _uiState.value = UiState.Error(e.toString())
                        logger.d(TAG, e.toString())
                    }.collect {
                        _uiState.value = UiState.Success(it)
                        logger.d(TAG, it.toString())
                    }
            } else {
                _uiState.value = UiState.Error(AppConstant.NETWORK_ERROR)
            }

        }
    }

    private fun generateAuthenticationObject(
        email: String,
        password: String
    ): AuthenticationEntity {
        return AuthenticationEntity(email = email, password = password)
    }
}