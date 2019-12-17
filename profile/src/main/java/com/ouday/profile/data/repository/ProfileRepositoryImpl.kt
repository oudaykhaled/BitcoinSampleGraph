package com.ouday.profile.data.repository

import androidx.lifecycle.liveData
import com.ouday.profile.data.remote.source.ProfileDataSource
import javax.inject.Inject
import com.ouday.core.network.Result

class ProfileRepositoryImpl @Inject constructor(private val remoteDataSource: ProfileDataSource) :
    ProfileRepository {

    override suspend fun requestLogin(username: String, password: String) = liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.requestLogin(username, password)
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


}