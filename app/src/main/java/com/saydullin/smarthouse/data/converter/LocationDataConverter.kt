package com.saydullin.smarthouse.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.saydullin.smarthouse.data.model.LocationData

@ProvidedTypeConverter
class LocationDataConverter {

    @TypeConverter
    fun fromLocationToString(locationData: LocationData): String? {
        return Gson().toJson(locationData)
    }

    @TypeConverter
    fun fromStringToLocationData(str: String?): LocationData? {
        val listType = object: TypeToken<LocationData>(){}.type
        return Gson().fromJson(str, listType)
    }

}