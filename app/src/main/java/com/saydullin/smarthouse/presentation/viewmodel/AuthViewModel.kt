package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.domain.usecase.auth.LogOutUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignInUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignUpUseCase
import com.saydullin.smarthouse.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
//    private val signUpUseCase: SignUpUseCase,
//    private val logOutUseCase: LogOutUseCase,
): ViewModel() {

    private val _isAuthenticated = mutableStateOf(false)
    val isAuthenticated = _isAuthenticated

    fun signUp(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val signUpResult = signUpUseCase.execute(login, password)
            _isAuthenticated.value = signUpResult is Resource.Success
        }
    }

}


