package com.sachin_singh_dighan.inspection_app.di.component

import com.sachin_singh_dighan.inspection_app.InspectionApplication
import com.sachin_singh_dighan.inspection_app.data.api.NetworkService
import com.sachin_singh_dighan.inspection_app.data.repository.LoginRepository
import com.sachin_singh_dighan.inspection_app.data.repository.RegisterRepository
import com.sachin_singh_dighan.inspection_app.di.module.ApplicationModule
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: InspectionApplication)
    fun getNetworkService(): NetworkService
    fun getLoginRepository(): LoginRepository
    fun getRegisterRepository(): RegisterRepository
    fun getNetworkHelper(): NetworkHelper
    fun getLogger(): Logger

}