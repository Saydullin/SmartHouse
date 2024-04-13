package com.saydullin.smarthouse.domain.usecase.apartment

import com.saydullin.smarthouse.domain.model.ApartmentUserRate
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class RateApartmentUseCase @Inject constructor(
    private val apartmentRepository: ApartmentsRepository
) {

    suspend fun execute(apartmentUserRate: ApartmentUserRate): Resource<Unit> {
        return apartmentRepository.rateApartment(apartmentUserRate)
    }

}