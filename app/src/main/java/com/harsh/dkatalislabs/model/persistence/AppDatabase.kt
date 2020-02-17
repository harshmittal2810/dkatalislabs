package com.harsh.dkatalislabs.model.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.model.persistence.daos.UserDao
import com.harsh.dkatalislabs.model.persistence.typeconverters.LocationUserConverter
import com.harsh.dkatalislabs.model.persistence.typeconverters.NameUserConverter

@Database(
    entities = [User::class],
    version = 1
)
@TypeConverters(
    LocationUserConverter::class,
    NameUserConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}