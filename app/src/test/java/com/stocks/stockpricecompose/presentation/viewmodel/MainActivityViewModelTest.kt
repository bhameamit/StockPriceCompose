package com.stocks.stockpricecompose.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stocks.stockpricecompose.data.stock.utils.DataResponse
import com.stocks.stockpricecompose.data.stock.utils.MetaResponse
import com.stocks.stockpricecompose.data.stock.utils.StockResponse
import com.stocks.stockpricecompose.domain.common.BaseResult
import com.stocks.stockpricecompose.domain.stocks.entity.StockEntity
import com.stocks.stockpricecompose.domain.stocks.interfaces.StockRepository
import com.stocks.stockpricecompose.domain.stocks.usecase.GetAllStocksUseCase
import com.stocks.stockpricecompose.presentation.viewmodel.utils.TestCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getAllStocksUseCase: GetAllStocksUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchallstocks_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            var stockEntity = StockEntity("Apple", "USD", 133.3)
            var stocks = mutableListOf<StockEntity>()
            stocks.add(stockEntity)
            val result = BaseResult.Success<List<StockEntity>>(stocks)
            val flow = flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
                emit(result)
            }
            doReturn(flow)
                .`when`(getAllStocksUseCase)
                .invoke()
            val viewModel = MainActivityViewModel(getAllStocksUseCase)
            verify(getAllStocksUseCase).invoke()

        }
    }
}