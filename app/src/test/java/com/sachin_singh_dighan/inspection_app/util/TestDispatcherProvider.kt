package com.sachin_singh_dighan.inspection_app.util

import com.sachin_singh_dighan.inspection_app.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcherProvider: DispatcherProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher  = UnconfinedTestDispatcher()
    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val io: CoroutineDispatcher
        get() = testDispatcher
    override val default: CoroutineDispatcher
        get() = testDispatcher

}