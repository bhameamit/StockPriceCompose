package com.stocks.datalayer.data.model

import com.google.gson.annotations.SerializedName

data class StockResponse <T,K>(
    @SerializedName("meta") var meta: T ,
    @SerializedName("data") var data: K

)