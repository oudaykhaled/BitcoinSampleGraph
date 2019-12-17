package com.ouday.bitcoin.data.repository

import androidx.lifecycle.LiveData
import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.core.network.Result

interface TransactionRepository {
    suspend fun requestTransactions(weeks: Int, rollingAverage: Int): LiveData<Result<TransactionResponse>>
}