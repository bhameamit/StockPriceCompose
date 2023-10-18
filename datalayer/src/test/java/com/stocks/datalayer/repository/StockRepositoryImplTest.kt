package com.stocks.datalayer.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.repository.StockRepositoryImpl
import com.stocks.datalayer.data.repository.interfaces.StockAPI
import com.stocks.datalayer.data.utils.network.BaseResult
import com.stocks.datalayer.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class StockRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var stockAPI: StockAPI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getAllStocks_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            var metaResponse = MetaResponse(1,2)
            var dataResponse = DataResponse("","",",","","","",100.00,100.00,100.00,100.00,100.00,100.00,100.00,100.00,"",100.00,3,false,"")
            var datalist = mutableListOf<DataResponse>()
            datalist.add(dataResponse)
            var stocks: StockResponse<MetaResponse, List<DataResponse>> = StockResponse(metaResponse, datalist)
            Mockito.doReturn(stocks)
                .`when`(stockAPI)
                .getAllStocks()
            val viewModel = StockRepositoryImpl(stockAPI)
            viewModel.getAllStocks()
            Mockito.verify(stockAPI)

        }
    }
}