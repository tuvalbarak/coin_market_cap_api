package com.example.coinmarketcapapi.repos

import com.example.coinmarketcapapi.models.Coin
import com.example.coinmarketcapapi.remote.listApi

/**
 * The Repo contains suspend functions to enable async performance using Coroutines.
 */
interface CoinsListRepo {
    suspend fun getAllCoins() : List<Coin>?
}

internal object CoinsListRepoImpl : CoinsListRepo {
    private const val apiKey = "f3e2338b-ad85-4243-8408-595025488db3"

    override suspend fun getAllCoins() =
            listApi.getAllCoins(apiKey).body()?.data

}