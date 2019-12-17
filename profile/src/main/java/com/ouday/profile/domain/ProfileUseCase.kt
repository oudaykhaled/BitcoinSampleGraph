package com.ouday.profile.domain

import androidx.lifecycle.LiveData
import com.ouday.profile.data.model.Profile
import com.ouday.core.network.Result

interface ProfileUseCase {

    suspend fun requestLogin(username: String, password: String): LiveData<Result<Profile>>

}