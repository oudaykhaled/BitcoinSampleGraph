package com.ouday.bitcoin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ouday.core.di.modules.ViewModelKey
import com.ouday.core.presentation.ViewModelFactory
import com.ouday.bitcoin.presentation.viewmodel.TransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TransactionPresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindsViewModel(viewModel: TransactionViewModel): ViewModel

}
