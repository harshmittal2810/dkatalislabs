package com.harsh.dkatalislabs.model.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harsh.dkatalislabs.model.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUser(username: String): User

    @Query("DELETE FROM user WHERE username = :username")
    fun deleteByUserId(username: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long
}