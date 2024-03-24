package com.saydullin.smarthouse.domain.usecase.apartment

import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import javax.inject.Inject

class SaveApartmentFavouriteUseCase @Inject constructor(
    private val apartmentsRepository: ApartmentsRepository
) {

    suspend fun execute(apartmentFavourite: ApartmentFavourite): Resource<Unit> {
        return apartmentsRepository.saveFavouriteApartment(apartmentFavourite)
    }

}


