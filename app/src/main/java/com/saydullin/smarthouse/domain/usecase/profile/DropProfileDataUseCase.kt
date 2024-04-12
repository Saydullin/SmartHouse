package com.saydullin.smarthouse.domain.usecase.profile

import com.saydullin.smarthouse.domain.repository.ProfileRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class DropProfileDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend fun execute(): Resource<Unit> {
        return profileRepository.dropUserData()
    }

}