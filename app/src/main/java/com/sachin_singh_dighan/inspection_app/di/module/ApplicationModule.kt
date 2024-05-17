package com.sachin_singh_dighan.inspection_app.di.module

import android.content.Context
import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.di.ApplicationContext
import com.sachin_singh_dighan.inspection_app.di.BaseUrl
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelperImpl
import com.sachin_singh_dighan.inspection_app.utils.logger.AppLogger
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
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

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return AppLogger()
    }
}