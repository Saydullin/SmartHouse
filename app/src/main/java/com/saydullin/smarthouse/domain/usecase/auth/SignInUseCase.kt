package com.saydullin.smarthouse.domain.usecase.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class SignInUseCase {

    suspend fun execute(login: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(login, password).await()
    }

}


