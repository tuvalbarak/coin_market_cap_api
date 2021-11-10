package com.example.coinmarketcapapi.remote

import com.example.coinmarketcapapi.models.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//In this interface the entire endpoints function should be implemented.
interface ListEndPoints {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getAllCoins(
        @Query("CMC_PRO_API_KEY") apiKey: String
    ): Response<CoinResponse>
}