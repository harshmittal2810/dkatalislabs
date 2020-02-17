package com.harsh.dkatalislabs.model.repositories

import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.model.entities.UserListModel
import io.reactivex.Completable
import io.reactivex.Single

interface UsersRepository {
    fun getRandomUser(): Single<UserListModel>
    fun saveUser(user: User): Completable
    fun getUser(): Single<List<User>>

}