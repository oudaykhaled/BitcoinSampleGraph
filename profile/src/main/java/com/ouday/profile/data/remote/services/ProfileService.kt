package com.ouday.profile.data.remote.services

import com.ouday.profile.data.model.Profile
import com.ouday.profile.data.remote.services.ProfileEndPoints
import com.ouday.profile.data.remote.services.requests.LoginRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {

    @POST("https://login.free.beeceptor.com${ProfileEndPoints.login}")
    fun loginAsync(@Body loginRequest: LoginRequest): Deferred<Response<Profile>>

}
