package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.domain.usecase.profile.GetProfileDataUseCase
import com.saydullin.smarthouse.domain.usecase.profile.SaveProfileDataUseCase
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val saveProfileDataUseCase: SaveProfileDataUseCase,
) : ViewModel() {

    private val _userData = mutableStateOf<UserData?>(null)
    private val _loading = mutableStateOf(false)
    private val _error = mutableStateOf<StatusCode?>(null)
    val userData = _userData
    val loading = _loading
    val error = _error

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val userDataRes = getProfileDataUseCase.execute()
            _userData.value = userDataRes.data

            if (userDataRes.data == null || userDataRes is Resource.Error) {
                _error.value = userDataRes.statusCode
            }
            _loading.value = false
        }
    }

    fun saveUserData(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val userDataRes = saveProfileDataUseCase.execute(userData)

            if (userDataRes is Resource.Error) {
                _error.value = userDataRes.statusCode
            }
            _loading.value = false
        }
    }

}


