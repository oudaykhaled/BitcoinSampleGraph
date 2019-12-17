package com.ouday.profile.domain

import com.ouday.profile.data.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(private val profileRepository: ProfileRepository) :
    ProfileUseCase {

    override suspend fun requestLogin(
        username: String,
        password: String
    ) = profileRepository.requestLogin(username, password)

}