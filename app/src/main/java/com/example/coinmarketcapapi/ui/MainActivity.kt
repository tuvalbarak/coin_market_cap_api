package com.example.coinmarketcapapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinmarketcapapi.Initializer
import com.example.coinmarketcapapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Calling init function that will perform initial actions for running the app.
        Initializer.init(application)
        setContentView(R.layout.activity_main)
    }
}