package com.example.coinmarketcapapi.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmarketcapapi.R
import com.example.coinmarketcapapi.models.Coin
import com.example.coinmarketcapapi.utils.format
import com.example.coinmarketcapapi.utils.gone
import com.example.coinmarketcapapi.utils.show
import kotlinx.android.synthetic.main.holder_row_coin.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Using Diffutils to compare between two articles.
 */
object CoinItemDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldCoin: Coin, newCoin: Coin) = oldCoin.symbol == newCoin.symbol
    override fun areContentsTheSame(oldCoin: Coin, newCoin: Coin) = oldCoin.symbol == newCoin.symbol
}

/**
 * @property itemView - current item in the recyclerview.
 * @property onCoinClickListener - lambda function for click handling.
 * This class is responsible for binding the data for each row in the recyclerview.
 */
class CoinsViewHolder(itemView: View, private val onCoinClickListener: (coin: Coin) -> Unit) : RecyclerView.ViewHolder(itemView) {

    //Binding data
    fun bind(coin: Coin) {

        val notAvailable = "N/A"

        //Parsing the date
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(coin.last_updated)
        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date ?: "")

        itemView.apply {
            //Binding the default data
            holder_row_coin_symbol.text = if(coin.name != "") coin.symbol else notAvailable
            holder_row_coin_last_updated.text = if(coin.last_updated != "") formattedDate else notAvailable
            holder_row_coin_num_market_pairs.text = if(coin.num_market_pairs != 0.0) coin.num_market_pairs.format() else notAvailable
            holder_row_coin_max_supply.text = if(coin.max_supply != 0.0) coin.max_supply.format() else notAvailable

            //Click listener for each coin
            this.setOnClickListener {
                coin.let { coinClicked -> onCoinClickListener.invoke(coinClicked) }
            }

            //Binding the expandable data
            if(coin.isExpanded) {
                val fiatSign = "$"
                val percentSign = "%"

                holder_row_coin_sub_view.show()
                holder_row_coin_name_value.text = coin.name
                holder_row_coin_price_value.text = fiatSign + coin.quote.USD.price.format()
                holder_row_coin_market_cap_dominance_value.text = coin.quote.USD.market_cap_dominance.format() + percentSign
                holder_row_coin_volume_24h_value.text = coin.quote.USD.volume_change_24h.format() + percentSign
                //Changing the volume color according to its value
                when {
                    coin.quote.USD.volume_change_24h > 0 ->
                        holder_row_coin_volume_24h_value.setTextColor(resources.getColor(R.color.positive, null))
                    coin.quote.USD.volume_change_24h < 0 ->
                        holder_row_coin_volume_24h_value.setTextColor(resources.getColor(R.color.negative, null))
                    else ->
                        holder_row_coin_volume_24h_value.setTextColor(resources.getColor(R.color.black, null))
                }
            }
            else {
                holder_row_coin_sub_view.gone()
            }
        }
    }

}

/**
 * Overriding ListAdapter's functions.
 */
class CoinsAdapter(private val onCoinClickListener: (coin: Coin) -> Unit) : ListAdapter<Coin, CoinsViewHolder>(CoinItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder =
        CoinsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.holder_row_coin, parent, false), onCoinClickListener
        )
    //Could have done binding here as well, chose to do it inside the ViewHolder for cleaner code.
    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bind(getItem(position))

        //Getting current item
        val coin = getItem(position)
        //Expandable recyclerview -
        holder.itemView.setOnClickListener {
            coin.isExpanded = !coin.isExpanded //Toggling the expanded state
            notifyItemChanged(position) //Notifying that the coin state was changed
        }
    }
}