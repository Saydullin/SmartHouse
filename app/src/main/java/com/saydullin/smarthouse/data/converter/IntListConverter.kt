package com.saydullin.smarthouse.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

@ProvidedTypeConverter
class IntListConverter {

    @TypeConverter
    fun fromIntListToString(list: List<Int>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToIntList(str: String?): List<Int>? {
        val listType = object: TypeToken<List<Int>>(){}.type
        return Gson().fromJson(str, listType)
    }

}