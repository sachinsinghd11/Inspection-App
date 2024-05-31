package com.sachin_singh_dighan.inspection_app.ui.inspection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import com.sachin_singh_dighan.inspection_app.data.repository.InspectionRepository
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.utils.AppConstant
import com.sachin_singh_dighan.inspection_app.utils.DispatcherProvider
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class InspectionViewModel(
    private val inspectionRepository: InspectionRepository,
    private val networkHelper: NetworkHelper,
    private val logger: Logger,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    companion object {
        const val TAG = "InspectionViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<InspectionEntity>>(UiState.Loading)
    val uiState: MutableStateFlow<UiState<InspectionEntity>> = _uiState

    init {
        fetchInspection()
    }

    private fun fetchInspection() {
        viewModelScope.launch {
            if (networkHelper.isNetworkAvailable()) {
                inspectionRepository.fetchInspections()
                    .flowOn(dispatcherProvider.io)
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
}