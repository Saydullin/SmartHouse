package com.saydullin.smarthouse.data.repository.apartments

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import kotlinx.coroutines.CompletableDeferred

class ApartmentsRepositoryImpl : ApartmentsRepository {

    override suspend fun getApartments(): Resource<List<Apartment>> {
        val db = FirebaseFirestore.getInstance()
        val deferred = CompletableDeferred<Resource<List<Apartment>>>()
        val apartmentsList = ArrayList<Apartment>()

        try {
            val apartments = db.collection("apartments")
            apartments.get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val apartment = document.toObject<Apartment>()
                        apartmentsList.add(apartment)
                    }
                    deferred.complete(
                        Resource.Success(data = apartmentsList)
                    )
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return deferred.await()
    }

}


