package com.saydullin.smarthouse.domain.usecase.apartment

import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.model.ApartmentUserRate
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class GetApartmentRatesUseCase @Inject constructor(
    private val apartmentRepository: ApartmentsRepository
) {

    suspend fun execute(apartment: Apartment): Resource<List<ApartmentUserRate>> {
        return apartmentRepository.getApartmentRates(apartment)
    }

}