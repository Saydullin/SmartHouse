package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.domain.usecase.auth.DeleteUserUseCase
import com.saydullin.smarthouse.domain.usecase.auth.LogOutUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignInUseCase
import com.saydullin.smarthouse.domain.usecase.auth.SignUpUseCase
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
): ViewModel() {

    private val _error = mutableStateOf<StatusCode?>(null)
    private val _isAuthenticated = mutableStateOf(Firebase.auth.currentUser != null)
    private val _loading = mutableStateOf(false)
    private val _isSignIn = mutableStateOf(false)
    val isAuthenticated = _isAuthenticated
    val loading = _loading
    val isSignIn = _isSignIn
    val error = _error

    fun signUp(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val signUpResult = signUpUseCase.execute(login, password)
            _isAuthenticated.value = signUpResult is Resource.Success
            if (signUpResult is Resource.Error) {
                _error.value = signUpResult.statusCode
            }
            _loading.value = false
        }
    }

    fun resetAuth() {
        _isAuthenticated.value = false
    }

    fun deleteAccount(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val deleteRes = deleteUserUseCase.execute(email, pass)
            _isAuthenticated.value = deleteRes !is Resource.Success
            if (deleteRes is Resource.Error) {
                _error.value = deleteRes.statusCode
            }
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            val signInResult = logOutUseCase.execute()
            _isAuthenticated.value = signInResult !is Resource.Success
            if (signInResult is Resource.Error) {
                _error.value = signInResult.statusCode
            }
        }
    }

    fun resetError() {
        _error.value = null
    }

    fun signIn(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val signInResult = signInUseCase.execute(login, password)
            _isAuthenticated.value = signInResult is Resource.Success
            if (signInResult is Resource.Error) {
                _error.value = signInResult.statusCode
            }
            _loading.value = false
        }
    }

}


