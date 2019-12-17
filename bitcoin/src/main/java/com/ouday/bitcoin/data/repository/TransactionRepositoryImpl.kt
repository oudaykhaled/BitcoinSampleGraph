package com.ouday.bitcoin.data.repository

import androidx.lifecycle.liveData
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSource
import com.ouday.bitcoin.data.repository.TransactionRepository
import javax.inject.Inject
import com.ouday.core.network.Result

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource
) : TransactionRepository {

    override suspend fun requestTransactions(weeks: Int, rollingAverage: Int)= liveData {
        emit(Result.loading())
        try {
            var response = remoteDataSource.requestTransaction(weeks, rollingAverage)
            emit(Result.success(response))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

}