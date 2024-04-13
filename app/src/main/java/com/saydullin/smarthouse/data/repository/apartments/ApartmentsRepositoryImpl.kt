package com.saydullin.smarthouse.data.repository.apartments

import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.model.ApartmentUserRate
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.CompletableDeferred

class ApartmentsRepositoryImpl : ApartmentsRepository {

    private val db = FirebaseFirestore.getInstance()

    override suspend fun getApartments(): Resource<List<Apartment>> {
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

    override suspend fun saveApartment(apartment: Apartment): Resource<Unit> {
        val document = "${apartment.authorUID}_doc_${apartment.id}"

        return try {
            db.collection("apartments").document(document).set(apartment)

            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()

            Resource.Error(
                statusCode = StatusCode.SERVER_ERROR
            )
        }
    }

    override suspend fun saveFavouriteApartment(apartmentFavourite: ApartmentFavourite): Resource<Unit> {
        val document = "${apartmentFavourite.uid}_doc_${apartmentFavourite.apartmentId}"

        return try {
            db.collection("favourite").document(document).set(apartmentFavourite)

            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()

            Resource.Error(
                statusCode = StatusCode.SERVER_ERROR
            )
        }
    }

    override suspend fun rateApartment(apartmentUserRate: ApartmentUserRate): Resource<Unit> {
        val document = "${apartmentUserRate.authorUID}_doc_${apartmentUserRate.apartmentId}"

        return try {
            db.collection("apartmentRate").document(document).set(apartmentUserRate)

            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()

            Resource.Error(
                statusCode = StatusCode.SERVER_ERROR
            )
        }
    }

    override suspend fun getApartmentRates(apartment: Apartment): Resource<List<ApartmentUserRate>> {
        val deferred = CompletableDeferred<Resource<List<ApartmentUserRate>>>()
        val apartmentRatesList = ArrayList<ApartmentUserRate>()

        try {
            val apartments = db.collection("apartmentRate").where(
                Filter.equalTo("apartmentId", apartment.id)
            )

            apartments.get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        apartmentRatesList.add(document.toObject<ApartmentUserRate>())
                    }
                    deferred.complete(
                        Resource.Success(data = apartmentRatesList)
                    )
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return deferred.await()
    }

}


