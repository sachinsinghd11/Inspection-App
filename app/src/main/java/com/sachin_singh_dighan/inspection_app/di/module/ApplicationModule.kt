package com.sachin_singh_dighan.inspection_app.di.module

import android.content.Context
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.di.ApplicationContext
import com.sachin_singh_dighan.inspection_app.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: InspectionApplication) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "http://127.0.0.1:5001"

    @Provides
    @Singleton
    fun provideGsonFactoryConvertor(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build()
            .create(NetworkService::class.java)
    }
}