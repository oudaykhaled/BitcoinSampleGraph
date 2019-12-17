package com.ouday.bitcoin.di

import com.ouday.bitcoin.data.repository.TransactionRepository
import com.ouday.bitcoin.data.repository.TransactionRepositoryImpl
import com.ouday.bitcoin.domain.TransactionUseCase
import com.ouday.bitcoin.domain.TransactionUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class TransactionDomainModule {

    @Binds
    abstract fun bindTransactionUseCase(
        useCaseImpl: TransactionUseCaseImpl
    ): TransactionUseCase

    @Binds
    abstract fun bindRepo(
        repoImpl: TransactionRepositoryImpl
    ): TransactionRepository
}
