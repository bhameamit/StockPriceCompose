package com.stocks.stockpricecompose.data.stock.interfaces

import com.stocks.stockpricecompose.data.stock.utils.DataResponse
import com.stocks.stockpricecompose.data.stock.utils.MetaResponse
import com.stocks.stockpricecompose.data.stock.utils.StockResponse
import retrofit2.http.GET

interface StockAPI {
    @GET("quote?symbols=AAPL,TSLA&api_token=XCgmh9ZXfumrL3mVVWfYLk1SRdxKCsSfXIzjRH9M")
    suspend fun getAllStocks(): StockResponse<MetaResponse, List<DataResponse>>
}