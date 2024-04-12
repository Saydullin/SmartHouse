package com.saydullin.smarthouse.domain.repository

import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.domain.utils.Resource

interface ProfileRepository {

    suspend fun getUserData(): Resource<UserData>

    suspend fun saveUserData(userData: UserData): Resource<Unit>

    suspend fun dropUserData(): Resource<Unit>

}