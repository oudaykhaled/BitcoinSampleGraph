package com.ouday.bitcoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ouday.bitcoin.data.model.TransactionResponse
import com.ouday.bitcoin.data.remote.service.TransactionService
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSource
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class BitcoinRemoteDataSourceTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = com.ouday.profile.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var remoteDataSource: TransactionRemoteDataSource

    lateinit var service: TransactionService

    @Before
    fun init() {

        service = mock {
            onBlocking {
                getTransactionsAsync("5weeks", "50hours", "json")
            } doReturn GlobalScope.async {
                Response.success(BitcoinMock.response)
            }
        }
        remoteDataSource = TransactionRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testgetTransactionsAsyncReturnSuccess() = runBlocking {
        val result = remoteDataSource.requestTransaction(5, 50)
        assert(result == BitcoinMock.response)
    }

}