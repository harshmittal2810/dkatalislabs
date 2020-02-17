package com.harsh.dkatalislabs.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserList(
    val seed: String,
    val user: User,
    val version: String
)

@Entity(tableName = "user")
data class User(
    val SSN: String,
    val cell: String,
    val dob: String,
    val email: String,
    val gender: String,
    val location: Location,
    val md5: String,
    val name: Name,
    val password: String,
    val phone: String,
    val picture: String,
    val registered: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    @PrimaryKey
    val username: String
) {
    constructor() : this(
        "", "", "", "", "", Location("", "", "", ""),
        "", Name("", "", ""), "", "", "", "", "", "", "", ""
    )
}

data class Location(
    val city: String,
    val state: String,
    val street: String,
    val zip: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)