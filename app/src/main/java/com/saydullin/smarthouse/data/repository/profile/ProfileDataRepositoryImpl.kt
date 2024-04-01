package com.saydullin.smarthouse.data.repository.profile

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.domain.repository.ProfileRepository
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.tasks.await

class ProfileDataRepositoryImpl : ProfileRepository {

    private val db = FirebaseFirestore.getInstance()

    override suspend fun getUserData(): Resource<UserData> {
        val uid = Firebase.auth.currentUser?.uid

        if (uid.isNullOrEmpty())
            return Resource.Error(
                statusCode = StatusCode.USER_NOT_AUTHENTICATED
            )
        val user = db.collection("users").document(uid)
        val document = user.get().await()
        val userData = document.toObject<UserData>()
        return Resource.Success(userData)

    }

    override suspend fun saveUserData(userData: UserData): Resource<Unit> {
        val uid = Firebase.auth.currentUser?.uid

        if (uid.isNullOrEmpty())
            return Resource.Error(
                statusCode = StatusCode.USER_NOT_AUTHENTICATED
            )
        return try {
            db.collection("users").document(uid).set(userData)

            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()

            Resource.Error(
                statusCode = StatusCode.SERVER_ERROR
            )
        }
    }

}