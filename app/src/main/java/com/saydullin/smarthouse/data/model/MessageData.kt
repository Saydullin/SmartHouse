package com.saydullin.smarthouse.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MessageData(
    val id: Int? = null,
    val title: String? = null
)