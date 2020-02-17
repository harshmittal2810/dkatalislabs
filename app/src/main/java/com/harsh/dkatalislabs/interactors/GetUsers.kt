package com.harsh.dkatalislabs.interactors

import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.model.repositories.UsersRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GetUsers @Inject constructor(private val usersRepository: UsersRepository) {

    fun execute(): Single<User> {
        val loginList = usersRepository.getRandomUser()
        return loginList.map {
            val items = it.results[0].user
            items
        }
    }

    fun saveUser(userData: User): Completable {
        return usersRepository.saveUser(userData)
    }

    fun getUser(): Single<List<User>> {
        return usersRepository.getUser()
    }

}