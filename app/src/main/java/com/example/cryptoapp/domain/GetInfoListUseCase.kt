package com.example.cryptoapp.domain

class GetInfoListUseCase(private val repository: CoinRepository) {

    operator fun invoke() = repository.getCoinInfoList()

}