package com.sachin_singh_dighan.inspection_app.data.local.dao

import com.sachin_singh_dighan.inspection_app.data.local.entity.AreaEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import kotlinx.coroutines.flow.Flow

interface AreaDao {
    fun createInspection(areaEntity: AreaEntity){

    }


    fun getInspection(): Flow<AreaEntity>
}