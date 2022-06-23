package com.example.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.GetCoinInfoUseCase
import com.example.cryptoapp.domain.GetInfoListUseCase
import com.example.cryptoapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }


}

