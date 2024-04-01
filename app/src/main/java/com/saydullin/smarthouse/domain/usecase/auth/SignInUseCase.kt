package com.saydullin.smarthouse.domain.usecase.auth

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
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
                    statusCode = StatusCode.UNKNOWN_ERROR
                )
            }
        } catch (es: FirebaseAuthException) {
            Log.e("sady", "StatusCode ${es.errorCode}")
            val errorCode = try {
                StatusCode.valueOf(es.errorCode)
            } catch (e: Exception) {
                StatusCode.SERVER_ERROR
            }
            Resource.Error(
                statusCode = errorCode
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                statusCode = StatusCode.CONNECTION_ERROR
            )
        }
    }

}


