package com.harsh.dkatalislabs.presentation.presenters

import com.harsh.dkatalislabs.interactors.GetUsers
import com.harsh.dkatalislabs.presentation.view.FavUserView
import com.harsh.dkatalislabs.util.SchedulerProvider
import javax.inject.Inject

/**
 * Author: Harsh Mittal
 * Date:   2020-02-17
 * Github: https://github.com/harshmittal2810
 */
class FavUserPresenter @Inject constructor(
    private val getUsers: GetUsers,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<FavUserView>() {
    private var loading = false

    fun getRandomUserList() {
        getUsers.getUser()
            .subscribeOn(schedulerProvider.ioScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribe({ users ->
                loading = false
                view?.addUsers(users)
                view?.hideLoading()
            },
                {
                    loading = false
                    view?.hideLoading()
                    view?.showEmptyListError()
                })
    }
}