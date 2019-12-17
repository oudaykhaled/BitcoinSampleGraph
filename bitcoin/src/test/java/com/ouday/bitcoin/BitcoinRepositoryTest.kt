package com.ouday.bitcoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ouday.core.network.Status
import com.ouday.bitcoin.BitcoinMock.response
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSource
import com.ouday.bitcoin.data.repository.TransactionRepository
import com.ouday.bitcoin.data.repository.TransactionRepositoryImpl
import com.ouday.profile.LiveDataTestUtil
import com.ouday.profile.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BitcoinRepositoryTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repository: TransactionRepository

    @Mock
    lateinit var remoteDataSource: TransactionRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = TransactionRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testRequestTransactionReturnSuccess() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.requestTransaction(5, 50)).thenReturn(response)
        val result = repository.requestTransactions(5,50)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == response)
    }

}