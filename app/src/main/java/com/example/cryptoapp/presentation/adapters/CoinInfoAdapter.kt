package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener: OnCoinClickListener? = null

    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewLogoCoin: ImageView = itemView.findViewById(R.id.imageViewLogoCoin)
        val textViewSymbols: TextView = itemView.findViewById(R.id.textViewSymbols)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val textViewUpdate: TextView = itemView.findViewById(R.id.textViewUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coin_info,
            parent,
            false
        )
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
            with(holder) {
                    with(coin) {
                        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                        val dateTemplate = context.resources.getString(R.string.last_update_label)
                        textViewSymbols.text = String.format(symbolsTemplate,fromSymbol,toSymbol)
                        textViewPrice.text = price.toString()
                        textViewUpdate.text = String.format(dateTemplate, lastUpdate)
                        Picasso.get().load(imageUrl).into(imageViewLogoCoin)
                        itemView.setOnClickListener {
                            onCoinClickListener?.onCoinClick(this)
                    }
            }

        }
    }

    override fun getItemCount() = coinInfoList.size

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }

}