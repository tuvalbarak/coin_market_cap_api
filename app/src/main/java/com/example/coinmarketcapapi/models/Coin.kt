package com.example.coinmarketcapapi.models

//Using interface for scalability
interface ICoin {
    val symbol: String
    val last_updated: String
    val name: String
    val num_market_pairs: Double
    val max_supply: Double
    val quote: Quote
}

data class Coin (
        var isExpanded: Boolean,
        override val symbol: String,
        override val last_updated: String,
        override val name: String,
        override val num_market_pairs: Double,
        override val max_supply: Double,
        override val quote: Quote
) : ICoin

data class Quote (
        val USD: Usd
)

data class Usd (
        val volume_change_24h: Double,
        val market_cap_dominance: Double,
        val price: Double
)

//Wrapping the Coin with this class so I can get the data from Retrofit.
data class CoinResponse (
        val data: ArrayList<Coin>
)
