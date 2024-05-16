package com.sachin_singh_dighan.inspection_app

import android.app.Application

class InspectionApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
    }
}