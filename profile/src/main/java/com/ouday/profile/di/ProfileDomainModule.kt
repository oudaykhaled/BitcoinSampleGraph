package com.ouday.profile.di

import com.ouday.profile.data.repository.ProfileRepository
import com.ouday.profile.data.repository.ProfileRepositoryImpl
import com.ouday.profile.domain.ProfileUseCase
import com.ouday.profile.domain.ProfileUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ProfileDomainModule {

    @Binds
    abstract fun bindProfileUseCase(
        useCaseImpl: ProfileUseCaseImpl
    ): ProfileUseCase

    @Binds
    abstract fun bindRepo(
        repoImpl: ProfileRepositoryImpl
    ): ProfileRepository

}