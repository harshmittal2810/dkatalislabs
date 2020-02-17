package com.harsh.dkatalislabs.presentation.view

import com.harsh.dkatalislabs.model.entities.User

interface FavUserView {
    fun showLoading()
    fun hideLoading()
    fun addUsers(userData: List<User>)
    fun showEmptyListError()
}