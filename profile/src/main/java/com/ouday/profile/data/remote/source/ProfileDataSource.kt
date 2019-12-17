package com.ouday.profile.data.remote.source

import com.ouday.profile.data.model.Profile

interface ProfileDataSource {
    suspend fun requestLogin(username: String, password: String): Profile
}