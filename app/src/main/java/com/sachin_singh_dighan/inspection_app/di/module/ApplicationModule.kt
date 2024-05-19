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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: InspectionApplication) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "http://192.168.48.60"//127.0.0.1:5001"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): NetworkService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory)
            .client(client).build()
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