package com.ouday.bitcoin.domain

import androidx.lifecycle.LiveData
import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.core.network.Result

interface TransactionUseCase {
    suspend fun requestTransactions(weeks: Int, rollingAverage: Int): LiveData<Result<TransactionResponse>>
}