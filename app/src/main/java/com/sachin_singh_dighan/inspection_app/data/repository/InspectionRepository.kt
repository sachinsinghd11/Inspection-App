package com.sachin_singh_dighan.inspection_app.data.repository

import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.model.Authentication
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InspectionRepository @Inject constructor(private val networkService: NetworkService) {

    fun fetchInspections(): Flow<Inspection> {
        return flow {
            emit(networkService.startInspection())
        }.map {
            it
        }
    }
}