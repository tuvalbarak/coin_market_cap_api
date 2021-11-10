package com.example.coinmarketcapapi

import android.app.Application
import com.example.coinmarketcapapi.remote.RetrofitFactory
import com.example.coinmarketcapapi.repos.RepoFactory

/**
 * Contains necessary data in order to start the app.
 */
object Initializer {
   fun init(application: Application) {
       //Initializing the app context
       RepoFactory.context = application
       //Creating the required Retrofit services
       RetrofitFactory.configure()
   }


}