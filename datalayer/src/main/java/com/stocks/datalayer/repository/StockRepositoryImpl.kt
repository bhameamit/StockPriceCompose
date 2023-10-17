package com.stocks.datalayer.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.stocks.datalayer.data.repository.interfaces.StockAPI
import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockEntity
import com.stocks.datalayer.data.model.StockResponse
import com.stocks.datalayer.data.repository.interfaces.StockRepository
import com.stocks.datalayer.data.utils.network.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val stockAPI: StockAPI): StockRepository {
    val TAG = "StockRepositoryImpl"
    override suspend fun getAllStocks(): Flow<BaseResult<List<StockEntity>, StockResponse<MetaResponse, List<DataResponse>>>> {
        return flow {
            Log.d(TAG, "Received fetchAllStocks response: " + stockAPI.getAllStocks())
            val response = stockAPI.getAllStocks()
            Log.d(TAG, "Response: " + response)
            if(response!=null){
                Log.d(TAG, "Response: " + response)
                val meta = response.meta
                val stocksResponse = response.data as List<DataResponse>
                Log.d(TAG, "Response meta: " + meta)
                Log.d(TAG, "Response stocksResponse: " + stocksResponse)
                val stocks = mutableStateListOf<StockEntity>()
                var stockEntity:StockEntity?
                val it: ListIterator<DataResponse> = stocksResponse.listIterator()
                while (it.hasNext()) {
                    val s = it.next()
                    stockEntity = StockEntity(s.name, s.currency, s.price)
                    Log.d(TAG, "Response stockEntity: " + stockEntity)
                    stocks.add(stockEntity)
                }
                emit(BaseResult.Success(stocks))
            }else{
                //Do nothing
            }
        }

    }

}