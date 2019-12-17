package com.ouday.bitcoin.domain

import com.ouday.bitcoin.data.repository.TransactionRepository
import javax.inject.Inject

class TransactionUseCaseImpl @Inject constructor(private val repository: TransactionRepository) :
    TransactionUseCase {
    override suspend fun requestTransactions(weeks: Int, rollingAverage: Int) = repository.requestTransactions(weeks, rollingAverage)
}