package com.ouday.bitcoin.data.remote.service

import com.ouday.bitcoin.data.model.TransactionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TransactionService {

    @GET("https://api.blockchain.info/charts/transactions-per-second")
    fun getTransactionsAsync(
        @Query("timespan") timeSpan: String,
        @Query("rollingAverage") rollingAverage: String,
        @Query("format") format: String
    ): Deferred<Response<TransactionResponse>>

}