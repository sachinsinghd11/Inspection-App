package com.sachin_singh_dighan.inspection_app.ui.inspection

import app.cash.turbine.test
import com.sachin_singh_dighan.inspection_app.data.model.Inspection
import com.sachin_singh_dighan.inspection_app.data.repository.InspectionRepositoryImpl
import com.sachin_singh_dighan.inspection_app.domain.usecases.InspectionUseCase
import com.sachin_singh_dighan.inspection_app.ui.base.UiState
import com.sachin_singh_dighan.inspection_app.util.TestDispatcherProvider
import com.sachin_singh_dighan.inspection_app.utils.DispatcherProvider
import com.sachin_singh_dighan.inspection_app.utils.NetworkHelper
import com.sachin_singh_dighan.inspection_app.utils.logger.Logger
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class InspectionViewModelTest {

    @Mock
    private lateinit var inspectionUseCase: InspectionUseCase

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp(){
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchInspections_whenRepositoryResponseSuccess_shouldSetSuccessUiState(){
        runTest {
            doReturn(flowOf(emptyList<Inspection>()))
                .`when`(inspectionUseCase).invoke()
            val viewModel = InspectionViewModel(inspectionUseCase, networkHelper, logger, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Inspection>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(inspectionUseCase, times(1)).invoke()

        }
    }


}