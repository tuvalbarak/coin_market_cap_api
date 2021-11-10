package com.example.coinmarketcapapi.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinmarketcapapi.models.Coin
import com.example.coinmarketcapapi.repos.CoinsListRepo
import com.example.coinmarketcapapi.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val coinsListRepo: CoinsListRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    val coinsList = MutableLiveData<List<Coin>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            postValue(coinsListRepo.getAllCoins())
            state.postValue(States.Idle)
        }
    }

}