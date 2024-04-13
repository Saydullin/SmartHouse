package com.saydullin.smarthouse.domain.model

data class ApartmentUserRate(
    val id: String,
    val apartmentId: Int,
    val authorUID: String,
    val starCount: Int,
    val message: String,
) {

    companion object {

        fun getRate(
            apartmentId: Int,
            authorUID: String,
            starCount: Int,
            message: String,
        ): ApartmentUserRate {
            return ApartmentUserRate(
                id = "${authorUID}_doc_${apartmentId}",
                authorUID = authorUID,
                apartmentId = apartmentId,
                starCount = starCount,
                message = message,
            )
        }

    }

}


