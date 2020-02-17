package com.harsh.dkatalislabs.model.repositories

import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.model.entities.UserListModel
import com.harsh.dkatalislabs.model.persistence.daos.UserDao
import com.harsh.dkatalislabs.model.services.UserService
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter

class DefaultUserRepository(
    private val userService: UserService,
    private val userDao: UserDao
) : UsersRepository {

    private fun loadUsersFromNetwork(
        emitter: SingleEmitter<UserListModel>
    ) {
        try {
            val users = userService.getRandomPerson().execute().body()
            if (users != null) {
                emitter.onSuccess(users)
            } else {
                emitter.onError(Exception("No data received"))
            }
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }

    override fun getRandomUser(): Single<UserListModel> {
        return Single.create<UserListModel> { emitter: SingleEmitter<UserListModel> ->
            loadUsersFromNetwork(emitter)
        }
    }

    override fun saveUser(user: User): Completable {
        return Completable.create { emitter: CompletableEmitter ->
            saveOfflineUsers(user, emitter)
        }
    }

    override fun getUser(): Single<List<User>> {
        return Single.create<List<User>> { emitter: SingleEmitter<List<User>> ->
            loadOfflineUsers(emitter)
        }
    }

    private fun loadOfflineUsers(emitter: SingleEmitter<List<User>>) {
        val users = userDao.getUsers()
        if (users.isNotEmpty()) {
            emitter.onSuccess(users)
        } else {
            emitter.onError(Exception("Device is offline"))
        }
    }

    private fun saveOfflineUsers(
        user: User,
        emitter: CompletableEmitter
    ) {
        val users = userDao.insertUser(user)
        if (users >= 0) {
            emitter.onComplete()
        } else {
            emitter.onError(Exception("Device is offline"))
        }
    }
}