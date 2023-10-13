package com.stocks.stockpricecompose.domain.stocks.interfaces

import com.stocks.stockpricecompose.data.stock.utils.DataResponse
import com.stocks.stockpricecompose.data.stock.utils.MetaResponse
import com.stocks.stockpricecompose.data.stock.utils.StockResponse
import com.stocks.stockpricecompose.domain.common.BaseResult
import com.stocks.stockpricecompose.domain.stocks.entity.StockEntity
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getAllStocks(): Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>>
}