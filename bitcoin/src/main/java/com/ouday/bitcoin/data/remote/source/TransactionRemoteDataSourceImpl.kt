package com.ouday.bitcoin.data.remote.source

import com.ouday.bitcoin.data.remote.service.TransactionService
import com.ouday.core.di.qualifier.CoroutinesIO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TransactionRemoteDataSourceImpl  @Inject constructor(
    private val service: TransactionService,
    @CoroutinesIO private val context: CoroutineContext
) : TransactionRemoteDataSource {

    override suspend fun requestTransaction(weeks: Int, rollingAverage: Int) = withContext(context) {
        val response = service.getTransactionsAsync(
            timeSpan = "${weeks}weeks",
            rollingAverage = "${rollingAverage}hours",
            format = "json"
        ).await()
        if (response.isSuccessful)
            response.body() ?: throw Exception("No Response")
        else {
            throw Exception()
        }
    }

}