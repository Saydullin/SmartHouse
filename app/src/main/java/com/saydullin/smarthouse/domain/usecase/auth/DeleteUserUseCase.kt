package com.saydullin.smarthouse.domain.usecase.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor() {

    suspend fun execute(email: String, pass: String): Resource<Unit> {
        val user = Firebase.auth

        return try {
            val credential = EmailAuthProvider.getCredential(email, pass)
            user.currentUser?.reauthenticate(credential)?.await()
            user.currentUser?.delete()?.await()

            Resource.Success(Unit)
        } catch (es: FirebaseAuthException) {
            val errorCode = try {
                StatusCode.valueOf(es.errorCode)
            } catch (e: Exception) {
                StatusCode.SERVER_ERROR
            }
            Resource.Error(
                statusCode = errorCode
            )
        } catch (e: Exception) {
            Resource.Error(
                statusCode = StatusCode.SERVER_ERROR
            )
        }
    }

}


