package com.sachin_singh_dighan.inspection_app.di.component

import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.local.dao.InspectionDao
import com.sachin_singh_dighan.inspection_app.data.repository.InspectionRepository
import com.sachin_singh_dighan.inspection_app.data.repository.LoginRepository
import com.sachin_singh_dighan.inspection_app.data.repository.RegisterRepository
import com.sachin_singh_dighan.inspection_app.di.module.ApplicationModule
import com.sachin_singh_dighan.inspection_app.utils.DispatcherProvider
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import dagger.Component
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: InspectionApplication)
    fun getNetworkService(): NetworkService
    fun getLoginRepository(): LoginRepository
    fun getRegisterRepository(): RegisterRepository
    fun getInspectionRepository(): InspectionRepository
    fun getNetworkHelper(): NetworkHelper
    fun getLogger(): Logger

    fun getRealm(): Realm

    fun getInspectionDao(): InspectionDao

    fun getDispatcherProvider(): DispatcherProvider

    //fun getInspectionDatabaseService(): InspectionDatabaseService
    //fun getInspectionAppDatabase(): InspectionAppDatabase

}