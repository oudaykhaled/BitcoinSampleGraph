package com.ouday.bitcoin.di

import com.ouday.bitcoin.data.remote.service.TransactionService
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSource
import com.ouday.bitcoin.data.remote.source.TransactionRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [TransactionRemoteModule.Binders::class])
class TransactionRemoteModule {

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: TransactionRemoteDataSourceImpl
        ): TransactionRemoteDataSource

    }

    @Provides
    fun providesService(retrofit: Retrofit): TransactionService =
        retrofit.create(TransactionService::class.java)

}
