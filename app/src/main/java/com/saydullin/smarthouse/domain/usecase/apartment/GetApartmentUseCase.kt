package com.saydullin.smarthouse.domain.usecase.apartment

import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class GetApartmentUseCase @Inject constructor(
    private val apartmentRepository: ApartmentsRepository
) {

    suspend fun getAllApartments(): Resource<List<Apartment>> {
        return apartmentRepository.getApartments()
    }

}