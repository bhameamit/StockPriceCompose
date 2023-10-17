package com.stocks.datalayer.data.repository.interfaces


import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.utils.network.BaseResult
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getAllStocks(): Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>>
}