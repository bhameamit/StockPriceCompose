package com.stocks.domainlayer.usecase


import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.repository.interfaces.StockRepository
import com.stocks.datalayer.data.utils.network.BaseResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllStocksUseCase @Inject constructor(private val stockRepository: StockRepository){
    suspend fun invoke() : Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
        return stockRepository.getAllStocks()
    }
}