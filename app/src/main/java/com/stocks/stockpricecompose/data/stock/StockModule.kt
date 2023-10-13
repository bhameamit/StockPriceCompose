package com.stocks.stockpricecompose.data.stock

import com.stocks.stockpricecompose.data.network.NetworkModule
import com.stocks.stockpricecompose.data.stock.interfaces.StockAPI
import com.stocks.stockpricecompose.data.stock.repository.StockRepositoryImpl
import com.stocks.stockpricecompose.domain.stocks.interfaces.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class StockModule {

    @Singleton
    @Provides
    fun provideStockAPI(retrofit: Retrofit): StockAPI{
        return retrofit.create(StockAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideStockRepository(stockAPI: StockAPI): StockRepository{
        return StockRepositoryImpl(stockAPI)
    }

}