package com.sachin_singh_dighan.inspection_app.domain.usecases

import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import com.sachin_singh_dighan.inspection_app.domain.Repository.InspectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InspectionUseCase @Inject constructor(private val inspectionRepository: InspectionRepository){

    operator fun invoke(): Flow<InspectionEntity>{
        return inspectionRepository.fetchInspections()
    }
}