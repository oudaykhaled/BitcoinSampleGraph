package com.ouday.bitcoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ouday.bitcoin.BitcoinMock.response
import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.bitcoin.domain.TransactionUseCase
import com.ouday.bitcoin.presentation.viewmodel.TransactionViewModel
import com.ouday.core.network.Result
import com.ouday.core.network.Status
import com.ouday.profile.LiveDataTestUtil
import com.ouday.profile.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BitcoinViewModelTest{
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var TransactionViewModel: TransactionViewModel

    lateinit var useCase: TransactionUseCase


    @Before
    fun init(){
        useCase = mock ()
    }

    @Test
    fun testRequestTransactionWhenStatusIsLoading() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { requestTransactions(5, 50) } doReturn object : LiveData<Result<TransactionResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        TransactionViewModel = TransactionViewModel(useCase)
        TransactionViewModel.requestAllRates(5, 50)

        val result = TransactionViewModel.getTransactionLiveData()
        result.observeForever {}
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testRequestTransactionWhenStatusIsSuccess() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { requestTransactions(5, 50) } doReturn object : LiveData<Result<TransactionResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }
        TransactionViewModel = TransactionViewModel(useCase)
        TransactionViewModel.requestAllRates(5, 50)
        val result = TransactionViewModel.getTransactionLiveData()
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == response)

    }


    @Test
    fun testRequestTransactionWhenStatusIsFailed() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { requestTransactions(5, 50) } doReturn object : LiveData<Result<TransactionResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        TransactionViewModel = TransactionViewModel(useCase)
        TransactionViewModel.requestAllRates(5, 50)
        val result = TransactionViewModel.getTransactionLiveData()
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error")

    }

}