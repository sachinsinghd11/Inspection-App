package com.sachin_singh_dighan.inspection_app

import android.app.Application
import com.sachin_singh_dighan.inspection_app.di.component.ApplicationComponent
import com.sachin_singh_dighan.inspection_app.di.component.DaggerApplicationComponent
import com.sachin_singh_dighan.inspection_app.di.module.ApplicationModule

class InspectionApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
        applicationComponent.inject(this)
    }
}