package com.harsh.dkatalislabs.model.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harsh.dkatalislabs.model.entities.Location

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */
class LocationUserConverter {

    @TypeConverter
    fun fromLocationList(value: Location): String {
        val gson = Gson()
        val type = object : TypeToken<Location>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toLocationList(value: String): Location {
        val gson = Gson()
        val type = object : TypeToken<Location>() {}.type
        return gson.fromJson(value, type)
    }

}