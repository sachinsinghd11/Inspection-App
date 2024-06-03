package com.sachin_singh_dighan.inspection_app.domain.Repository

import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import kotlinx.coroutines.flow.Flow

interface InspectionRepository {
    fun fetchInspections(): Flow<InspectionEntity>
}