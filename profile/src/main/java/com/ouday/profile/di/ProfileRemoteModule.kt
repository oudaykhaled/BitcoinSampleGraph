package com.ouday.profile.di

import com.ouday.profile.data.remote.services.ProfileService
import com.ouday.profile.data.remote.source.ProfileDataSource
import com.ouday.profile.data.remote.source.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [ProfileRemoteModule.Binders::class])
class ProfileRemoteModule {

    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: ProfileDataSourceImpl
        ): ProfileDataSource
    }

    @Provides
    fun providesProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

}