package com.ouday.bitcoin.data.remote.source

import com.ouday.bitcoin.data.model.TransactionResponse

interface TransactionRemoteDataSource {
    suspend fun requestTransaction(weeks: Int, rollingAverage: Int): TransactionResponse
}