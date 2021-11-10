package com.example.coinmarketcapapi.repos

import android.app.Application

//Using the Factory design pattern to create a scalable solution that can maintain any number of repositories across the app.
object RepoFactory {
    lateinit var context: Application
    val listRepo: CoinsListRepo = CoinsListRepoImpl
}