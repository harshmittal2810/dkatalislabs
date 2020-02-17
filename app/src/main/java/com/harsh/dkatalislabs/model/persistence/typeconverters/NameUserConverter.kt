package com.harsh.dkatalislabs.model.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harsh.dkatalislabs.model.entities.Name

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */
class NameUserConverter {

    @TypeConverter
    fun fromNameList(value: Name): String {
        val gson = Gson()
        val type = object : TypeToken<Name>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toNameList(value: String): Name {
        val gson = Gson()
        val type = object : TypeToken<Name>() {}.type
        return gson.fromJson(value, type)
    }

}