package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)

        val fromSymbolTextView = findViewById<TextView>(R.id.fromSymbolTextView)
        val toSymbolTextView = findViewById<TextView>(R.id.toSymbolTextView)
        val imageViewDetailLogo = findViewById<ImageView>(R.id.imageViewDetailCoinLogo)
        val maxPriceTextView = findViewById<TextView>(R.id.maxPriceTextView)
        val minPriceTextView = findViewById<TextView>(R.id.minPriceTextView)
        val lastDealTextView = findViewById<TextView>(R.id.lastDealTextView)
        val lastUpdateTextView = findViewById<TextView>(R.id.lastUpdateTextView)

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CoinViewModel::class.java)
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this,  Observer {
                fromSymbolTextView.text = it.fromSymbol
                toSymbolTextView.text = it.toSymbol
                Picasso.get().load(it.getFullImageUrl()).into(imageViewDetailLogo)
                maxPriceTextView.text = it.highDay.toString()
                minPriceTextView.text = it.lowDay.toString()
                lastDealTextView.text = it.lastMarket
                lastUpdateTextView.text = it.getFormattedTime()
            })
        }



    }

    companion object{

        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context,CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL,fromSymbol)
            return intent
        }
    }
}