package com.saydullin.smarthouse.domain.usecase.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.tasks.await

class SignInUseCase {

    suspend fun execute(login: String, password: String): Resource<Unit> {
        val auth = Firebase.auth
        return try {
            val signInResult = auth.signInWithEmailAndPassword(login, password).await()
            if (signInResult.user != null) {
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


