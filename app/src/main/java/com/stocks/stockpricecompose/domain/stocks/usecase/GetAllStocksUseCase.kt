package com.stocks.stockpricecompose.domain.stocks.usecase

import com.stocks.stockpricecompose.data.stock.utils.DataResponse
import com.stocks.stockpricecompose.data.stock.utils.MetaResponse
import com.stocks.stockpricecompose.data.stock.utils.StockResponse
import com.stocks.stockpricecompose.domain.common.BaseResult
import com.stocks.stockpricecompose.domain.stocks.entity.StockEntity
import com.stocks.stockpricecompose.domain.stocks.interfaces.StockRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllStocksUseCase @Inject constructor(private val stockRepository: StockRepository){
    suspend fun invoke() : Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
        return stockRepository.getAllStocks()
    }
}