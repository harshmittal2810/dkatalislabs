package com.harsh.dkatalislabs.presentation.presenters

import android.annotation.SuppressLint
import com.harsh.dkatalislabs.interactors.GetUsers
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.presentation.view.UserView
import com.harsh.dkatalislabs.util.ConnectionHelper
import com.harsh.dkatalislabs.util.SchedulerProvider
import javax.inject.Inject

/**
 * Author: Harsh Mittal
 * Date:   2020-02-17
 * Github: https://github.com/harshmittal2810
 */
class UserPresenter @Inject constructor(
    private val getUsers: GetUsers,
    private val schedulerProvider: SchedulerProvider,
    private val connectionHelper: ConnectionHelper
) : BasePresenter<UserView>() {
    private var loading = false

    @SuppressLint("CheckResult")
    fun getRandomUser() {
        if (!connectionHelper.isOnline()) {
            view?.hideLoading()
            view?.showError("No Internet")
            return
        }
        loading = true
        getUsers.execute()
            .subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribe({ users ->
                loading = false
                view?.clearList()
                view?.hideEmptyListError()
                view?.addUsers(users)
                view?.hideLoading()
            },
                {
                    loading = false
                    view?.hideLoading()
                    view?.showEmptyListError()
                    view?.showError(it.message ?: "")
                })
    }

    fun saveUser(userData: User) {
        getUsers.saveUser(userData)
            .subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribe()
    }
}