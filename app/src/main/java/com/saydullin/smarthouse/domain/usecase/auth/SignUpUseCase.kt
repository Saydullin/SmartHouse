package com.saydullin.smarthouse.domain.usecase.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.tasks.await

class SignUpUseCase {

    suspend fun execute(login: String, pass: String): Resource<Unit> {
        val auth = Firebase.auth

        return try {
            val signUp = auth.createUserWithEmailAndPassword(login, pass).await()
            if (signUp.user != null) {
                Resource.Success(Unit)
            } else {
                Resource.Error(
                    statusCode = StatusCode.SERVER_ERROR
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                statusCode = StatusCode.CONNECTION_ERROR
            )
        }
    }
}


