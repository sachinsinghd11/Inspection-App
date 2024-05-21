package com.sachin_singh_dighan.inspection_app.data.api

import com.sachin_singh_dighan.inspection_app.data.local.entity.AuthenticationEntity
import com.sachin_singh_dighan.inspection_app.data.local.entity.InspectionEntity
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @POST("/api/register")
    suspend fun registerUser(@Body credentials: AuthenticationEntity): AuthenticationEntity

    @POST("/api/login")
    suspend fun loginUser(@Body credentials: AuthenticationEntity): AuthenticationEntity

    @GET("/api/inspections/start")
    suspend fun startInspection(): InspectionEntity

    @POST("/api/inspections/submit")
    suspend fun submitInspection(@Body inspection: Inspection): Inspection


    //Other Available EndPoints
    @GET("/api/generate_random_inspections/{id}")
    suspend fun generateRandomInspections(@Path("no") no: Int = 10): Inspection

    @GET("/api/random_inspection")
    suspend fun getRandomInspections(): Inspection

    @GET("/api/inspections/{id}")
    suspend fun getInspectionById(@Path("id") id: Int): Inspection

    @DELETE("/api/inspections/{id}")
    suspend fun deleteInspectionById(@Path("id") id: Int): Inspection

}