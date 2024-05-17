package com.sachin_singh_dighan.inspection_app.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sachin_singh_dighan.inspection_app.data.repository.LoginRepository
import com.sachin_singh_dighan.inspection_app.data.repository.RegisterRepository
import com.sachin_singh_dighan.inspection_app.di.ActivityContext
import com.sachin_singh_dighan.inspection_app.ui.base.ViewModelProviderFactory
import com.sachin_singh_dighan.inspection_app.ui.login.LoginViewModel
import com.sachin_singh_dighan.inspection_app.ui.register.RegisterViewModel
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideLoginViewModel(
        loginRepository: LoginRepository,
        networkHelper: NetworkHelper,
        logger: Logger
    ): LoginViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(loginRepository, networkHelper, logger)
        })[LoginViewModel::class.java]
    }

    @Provides
    fun provideRegisterViewModel(
        registerRepository: RegisterRepository,
        networkHelper: NetworkHelper,
        logger: Logger
    ): RegisterViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(RegisterViewModel::class) {
            RegisterViewModel(registerRepository, networkHelper, logger)
        })[RegisterViewModel::class.java]
    }
}