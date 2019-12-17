package com.ouday.profile.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ouday.core.di.modules.ViewModelKey
import com.ouday.core.presentation.ViewModelFactory
import com.ouday.profile.presentation.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfilePresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindsProfileViewModel(profileViewModel: ProfileViewModel): ViewModel
}