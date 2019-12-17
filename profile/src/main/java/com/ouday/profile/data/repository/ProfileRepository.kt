package com.ouday.profile.data.repository

import androidx.lifecycle.LiveData
import com.ouday.profile.data.model.Profile
import com.ouday.core.network.Result

interface ProfileRepository {
    suspend fun requestLogin(username: String, password: String): LiveData<Result<Profile>>
}