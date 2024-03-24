package com.saydullin.smarthouse.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
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
    private val signInUseCase: SignInUseCase,
//    private val logOutUseCase: LogOutUseCase,
): ViewModel() {

    private val _isAuthenticated = mutableStateOf(Firebase.auth.currentUser != null)
    private val _loading = mutableStateOf(false)
    private val _isSignIn = mutableStateOf(false)
    val isAuthenticated = _isAuthenticated
    val loading = _loading
    val isSignIn = _isSignIn

    fun signUp(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val signUpResult = signUpUseCase.execute(login, password)
            _isAuthenticated.value = signUpResult is Resource.Success
            _loading.value = false
        }
    }

    fun signOut() {
        _isAuthenticated.value = false
    }

    fun signIn(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val signInResult = signInUseCase.execute(login, password)
            _isAuthenticated.value = signInResult is Resource.Success
            _loading.value = false
        }
    }

}


