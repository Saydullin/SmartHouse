package com.saydullin.smarthouse.domain.usecase.profile

import com.saydullin.smarthouse.domain.model.UserData
import com.saydullin.smarthouse.domain.repository.ProfileRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class SaveProfileDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend fun execute(userData: UserData): Resource<Unit> {
        return profileRepository.saveUserData(userData)
    }

}