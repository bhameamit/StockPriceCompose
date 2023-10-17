package com.stocks.stockpricecompose.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.utils.network.BaseResult
import com.stocks.domainlayer.usecase.GetAllStocksUseCase
import com.stocks.stockpricecompose.presentation.viewmodel.utils.TestCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

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
            viewModel.fetchAllStocks()
            verify(getAllStocksUseCase)
        }
    }
}