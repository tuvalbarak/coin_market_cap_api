package com.example.coinmarketcapapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinmarketcapapi.R
import com.example.coinmarketcapapi.models.Coin
import com.example.coinmarketcapapi.ui.adapters.CoinsAdapter
import com.example.coinmarketcapapi.utils.States
import com.example.coinmarketcapapi.utils.gone
import com.example.coinmarketcapapi.utils.show
import com.example.coinmarketcapapi.viewmodels.ListViewModel
import com.example.coinmarketcapapi.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_coins_list.*


class CoinsListFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_coins_list
    override val logTag: String = "CoinsListFragment"

    //Using the ViewModelFactory I created to create a VM to this fragment.
    private val coinsListViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(ListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupState()
        setupCoinsList()
    }

    private fun setupRecyclerView() {

        //Divider between rows
        val dividerItemDecoration = DividerItemDecoration(fragment_coins_list_rv_recyclerview.context, LinearLayoutManager.VERTICAL)
        fragment_coins_list_rv_recyclerview.addItemDecoration(dividerItemDecoration)

        //Lambda function that will be sent to the adapter. It will be invoked from the adapter after every row click.
        val onCoinClicked: (coin: Coin) -> Unit = {
            //Do something
            Log.d(logTag, it.name)
        }

        //Binding the adapter with the recyclerview.
        fragment_coins_list_rv_recyclerview.adapter = CoinsAdapter(onCoinClicked)
    }

    //Observing the state of the app.
    private fun setupState() {
        coinsListViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                States.Loading -> fragment_coins_list_pb_progress_bar.show()
                States.Idle -> fragment_coins_list_pb_progress_bar.gone()
            }
        })
    }

    //Observing the coins list, after every change - updating the adapter using submitList -> only the relevant rows will be updated.
    private fun setupCoinsList() {
        coinsListViewModel.coinsList.observe(viewLifecycleOwner, Observer { coinsList ->
            (fragment_coins_list_rv_recyclerview.adapter as CoinsAdapter).submitList(coinsList)
            //If for some reason the list is empty, a message will be displayed to the user.
            fragment_coins_list_tv_empty_list.apply {
                if(coinsList.isEmpty()) show()
                else gone()
            }
        })
    }

}