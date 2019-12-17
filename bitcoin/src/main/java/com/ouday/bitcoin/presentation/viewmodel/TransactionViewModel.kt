package com.ouday.bitcoin.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.bitcoin.domain.TransactionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ouday.core.network.Result

class TransactionViewModel @Inject constructor(private val useCase: TransactionUseCase) : ViewModel() {

    private val TransactionLiveData = MediatorLiveData<Result<TransactionResponse>>()

    fun requestAllRates(weeks: Int, rollingAverage: Int) {
        viewModelScope.launch {
            TransactionLiveData.addSource(useCase.requestTransactions(weeks, rollingAverage)) {
                TransactionLiveData.value = it
            }
        }
    }

    fun getTransactionLiveData(): MediatorLiveData<Result<TransactionResponse>> {
        return TransactionLiveData
    }

}