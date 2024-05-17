package com.sachin_singh_dighan.inspection_app.di.component

import com.sachin_singh_dighan.inspection_app.di.ActivityScope
import com.sachin_singh_dighan.inspection_app.di.module.ActivityModule
import com.sachin_singh_dighan.inspection_app.ui.login.LoginActivity
import com.sachin_singh_dighan.inspection_app.ui.register.RegisterActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: LoginActivity)

    fun injectRegisterActivity(activity: RegisterActivity)
}