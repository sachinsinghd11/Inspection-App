package com.sachin_singh_dighan.inspection_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin_singh_dighan.inspection_app.data.model.Authentication
import com.sachin_singh_dighan.inspection_app.data.repository.LoginRepository
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.utils.AppConstant
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val networkHelper: NetworkHelper,
    private val logger: Logger
) : ViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<Authentication>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Authentication>> = _uiState


    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            if (networkHelper.isNetworkAvailable()) {
                loginRepository.loginUser(generateAuthenticationObject(email, password))
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

    private fun generateAuthenticationObject(email: String, password: String): Authentication {
        return Authentication(email = email, password = password)
    }
}