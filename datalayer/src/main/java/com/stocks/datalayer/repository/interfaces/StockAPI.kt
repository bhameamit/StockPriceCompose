package com.stocks.datalayer.data.repository.interfaces

import com.stocks.datalayer.data.model.DataResponse
import com.stocks.datalayer.data.model.MetaResponse
import com.stocks.datalayer.data.model.StockResponse
import retrofit2.http.GET

interface StockAPI {
    @GET("quote?symbols=AAPL,TSLA&api_token=XCgmh9ZXfumrL3mVVWfYLk1SRdxKCsSfXIzjRH9M")
    suspend fun getAllStocks(): StockResponse<MetaResponse, List<DataResponse>>
}