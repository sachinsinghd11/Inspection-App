package com.sachin_singh_dighan.inspection_app.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sachin_singh_dighan.inspection_app.data.repository.InspectionRepositoryImpl
import com.sachin_singh_dighan.inspection_app.di.ActivityContext
import com.sachin_singh_dighan.inspection_app.domain.usecases.InspectionUseCase
import com.sachin_singh_dighan.inspection_app.domain.usecases.LoginUseCase
import com.sachin_singh_dighan.inspection_app.domain.usecases.RegisterUseCase
import com.sachin_singh_dighan.inspection_app.ui.base.ViewModelProviderFactory
import com.sachin_singh_dighan.inspection_app.ui.inspection.AnswerChoicesAdapter
import com.sachin_singh_dighan.inspection_app.ui.inspection.InspectionAdapter
import com.sachin_singh_dighan.inspection_app.ui.inspection.InspectionViewModel
import com.sachin_singh_dighan.inspection_app.ui.inspection.QuestionsAdapter
import com.sachin_singh_dighan.inspection_app.ui.login.LoginViewModel
import com.sachin_singh_dighan.inspection_app.ui.register.RegisterViewModel
import com.sachin_singh_dighan.inspection_app.utils.DispatcherProvider
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import dagger.Module
import dagger.Provides
import java.util.ArrayList

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideLoginViewModel(
        loginUseCase: LoginUseCase,
        networkHelper: NetworkHelper,
        logger: Logger
    ): LoginViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(loginUseCase, networkHelper, logger)
        })[LoginViewModel::class.java]
    }

    @Provides
    fun provideRegisterViewModel(
        registerUseCase: RegisterUseCase,
        networkHelper: NetworkHelper,
        logger: Logger
    ): RegisterViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(RegisterViewModel::class) {
            RegisterViewModel(registerUseCase, networkHelper, logger)
        })[RegisterViewModel::class.java]
    }

    @Provides
    fun provideInspectionViewModel(
        inspectionUseCase: InspectionUseCase,
        networkHelper: NetworkHelper,
        logger: Logger,
        dispatcherProvider: DispatcherProvider,
    ): InspectionViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(InspectionViewModel::class) {
            InspectionViewModel(inspectionUseCase, networkHelper, logger, dispatcherProvider)
        })[InspectionViewModel::class.java]
    }

    @Provides
    fun provideInspectionAdapter() = InspectionAdapter(ArrayList())

    @Provides
    fun provideQuestionAdapter() = QuestionsAdapter(ArrayList())

    @Provides
    fun provideAnswerChoicesAdapter() = AnswerChoicesAdapter(ArrayList())
}