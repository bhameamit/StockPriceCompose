package com.stocks.datalayer.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stocks.datalayer.data.di.StockModule
import com.stocks.datalayer.data.repository.StockRepositoryImpl
import com.stocks.datalayer.data.repository.interfaces.StockAPI
import com.stocks.datalayer.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class StockModuleTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var stockAPI: StockAPI

    @Mock
    private lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun provideStockAPI_shouldReturnSuccess() {
        Mockito.doReturn(stockAPI)
            .`when`(retrofit)
            .create(StockAPI::class.java)
        val stockModule = StockModule()
        stockModule.provideStockAPI(retrofit)
        Mockito.verify(retrofit).create(StockAPI::class.java)
    }

    @Test
    fun provideStockRepository_shouldReturnSuccess() {
        val stockRepositoryImpl = StockRepositoryImpl(stockAPI)
        Mockito.verify(stockAPI)

    }
}