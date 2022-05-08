package com.example.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.pojo.CoinPriceInfo


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val adapter = CoinInfoAdapter(this)
        val recyclerViewCoinPriceList = findViewById<RecyclerView>(R.id.recyclerViewCoinPriceList)
        recyclerViewCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol

                )
                startActivity(intent)
            }

        }
                viewModel = ViewModelProvider
                    .AndroidViewModelFactory(application)
                    .create(CoinViewModel::class.java)
                viewModel.priceList.observe(this, Observer {
                    Log.d("TEST_DATA", "Success in Activity")
                    adapter.coinInfoList = it
                })

    }
}