package com.saydullin.smarthouse.data.repository.apartments

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.model.Location
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import kotlinx.coroutines.CompletableDeferred

class ApartmentsRepositoryImpl : ApartmentsRepository {

    override suspend fun getApartments(): Resource<List<Apartment>> {
        val db = FirebaseFirestore.getInstance()
        val deferred = CompletableDeferred<Resource<List<Apartment>>>()

        try {
            val apartments = db.collection("apartments").document("vOrmE4hlUHEOyFs1RDxr")
            apartments.get()
                .addOnSuccessListener { documentSnapshot ->
                    val apartment = documentSnapshot.toObject<Apartment>()
                    if (apartment == null) {
                        deferred.complete(
                            Resource.Error(
                                statusCode = StatusCode.SERVER_ERROR
                            )
                        )
                    } else {
                        deferred.complete(
                            Resource.Success(data = listOf(apartment))
                        )
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("sady", "error in getApartments Impl ${e.printStackTrace()}")
        }

        val def = deferred.await()

        Log.e("sady", "Res: ${def.data.toString()}")

        return def
    }

}


