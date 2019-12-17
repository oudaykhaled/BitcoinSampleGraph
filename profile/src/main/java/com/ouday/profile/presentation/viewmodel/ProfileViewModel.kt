package com.ouday.profile.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.profile.data.model.Profile
import com.ouday.profile.domain.ProfileUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ouday.core.network.Result

class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : ViewModel() {

    private val loginLiveData = MediatorLiveData<Result<Profile>>()

    fun requestLogin(username: String, password: String) {
        viewModelScope.launch {
            loginLiveData.addSource(useCase.requestLogin(username, password)) {
                loginLiveData.value = it
            }
        }
    }

    fun getLoginLiveData(): MediatorLiveData<Result<Profile>> {
        return loginLiveData
    }

}