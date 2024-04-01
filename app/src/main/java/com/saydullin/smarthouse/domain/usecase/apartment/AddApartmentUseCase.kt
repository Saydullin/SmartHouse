package com.saydullin.smarthouse.domain.usecase.apartment

import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class AddApartmentUseCase @Inject constructor(
    private val apartmentRepository: ApartmentsRepository
) {

    suspend fun addApartment(apartment: Apartment): Resource<Unit> {
        return apartmentRepository.saveApartment(apartment)
    }

}