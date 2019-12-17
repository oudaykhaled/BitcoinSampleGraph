package com.ouday.oudayn26.di.ioc

import com.ouday.core.ioc.ProfileIoc
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProfileIocProvider {

    @Provides
    @Singleton
    fun provideProfileIOC(): ProfileIoc {
        return ProfileIocImpl()
    }
}