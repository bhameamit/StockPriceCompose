package com.stocks.domainlayer.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.repository.interfaces.StockAPI
import com.stocks.datalayer.data.repository.interfaces.StockRepository
import com.stocks.datalayer.data.utils.network.BaseResult
import com.stocks.domainlayer.utils.TestCoroutineRule
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
class GetAllStocksUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var stockRepository: StockRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun invoke_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            var stockEntity = StockEntity("Apple", "USD", 133.3)
            var stocks = mutableListOf<StockEntity>()
            stocks.add(stockEntity)
            val result = BaseResult.Success<List<StockEntity>>(stocks)
            val flow =
                flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
                    emit(result)
                }
            Mockito.doReturn(flow)
                .`when`(stockRepository)
                .getAllStocks()
            val getAllStocksUseCase = GetAllStocksUseCase(stockRepository)
            getAllStocksUseCase.invoke()
            Mockito.verify(stockRepository)
        }
    }
}