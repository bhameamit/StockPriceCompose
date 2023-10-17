package com.stocks.datalayer.data.di

import com.stocks.datalayer.data.utils.network.NetworkModule
import com.stocks.datalayer.data.repository.interfaces.StockAPI
import com.stocks.datalayer.data.repository.StockRepositoryImpl
import com.stocks.datalayer.data.repository.interfaces.StockRepository

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
    fun provideStockRepository(stockAPI: StockAPI): StockRepository {
        return StockRepositoryImpl(stockAPI)
    }

}