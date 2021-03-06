package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.database.CoinInfoDAO
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl) : CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ) : CoinInfoDAO {
            return AppDatabase.getInstance(application).coinInfoDao()
        }
    }
}