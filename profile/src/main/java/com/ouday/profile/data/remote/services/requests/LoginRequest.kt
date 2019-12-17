package com.ouday.profile.data.remote.services.requests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    val username: String,
    val password: String
): Parcelable