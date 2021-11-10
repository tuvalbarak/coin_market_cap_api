package com.example.coinmarketcapapi.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Internal so only relevant module can access
internal lateinit var listApi: ListEndPoints

/**
 * Implemented Retrofit creation as a Singleton, Used the Factory design pattern to achieve reuse of my code scalability.
 * Implemented a generic create function to support code reuse and flexibility.
 */
object RetrofitFactory {

    private const val baseUrl = "https://pro-api.coinmarketcap.com/"
    private var retrofit: Retrofit? = null

    //Another endpoints should be initialized inside this function
    fun configure() {
        listApi = create(ListEndPoints::class.java)
    }

    //Generic function to create the Retrofit service
    private fun <T> create(service: Class<T>): T = retrofit?.create(service) ?: run {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit!!.create(service)
    }

}