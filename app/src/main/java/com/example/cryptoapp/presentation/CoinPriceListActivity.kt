package com.example.cryptoapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val adapter = CoinInfoAdapter(this)
        val recyclerViewCoinPriceList = findViewById<RecyclerView>(R.id.recyclerViewCoinPriceList)
        recyclerViewCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfo.fromSymbol

                )
                startActivity(intent)
            }

        }
                viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
                viewModel.coinInfoList.observe(this) {
                    Log.d("TEST_DATA", "Success in Activity")
                    adapter.coinInfoList = it
                }

    }
}