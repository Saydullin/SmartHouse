package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.domain.usecase.profile.DropProfileDataUseCase
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
    private val dropProfileDataUseCase: DropProfileDataUseCase,
) : ViewModel() {

    private val _userData = mutableStateOf<UserData?>(null)
    private val _loading = mutableStateOf(false)
    private val _error = mutableStateOf<StatusCode?>(null)
    private val _isSaved = mutableStateOf<Boolean?>(null)
    val userData = _userData
    val loading = _loading
    val error = _error
    val isSaved = _isSaved

    fun resetError() {
        _error.value = null
    }

    fun resetIsSaved() {
        _isSaved.value = null
    }

    fun dropUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val dropUserData = dropProfileDataUseCase.execute()
            if (dropUserData is Resource.Error) {
                _error.value = dropUserData.statusCode
            } else {
                _isSaved.value = true
            }
            _loading.value = false
        }
    }

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val userDataRes = getProfileDataUseCase.execute()
            _userData.value = userDataRes.data

            if (userDataRes is Resource.Error) {
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
            } else {
                _isSaved.value = true
            }
            _loading.value = false
        }
    }

}


