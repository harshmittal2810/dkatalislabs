package com.harsh.dkatalislabs.presentation.view

import com.harsh.dkatalislabs.model.entities.User

interface UserView {
    fun showLoading()
    fun hideLoading()
    fun addUsers(userData: User)
    fun showEmptyListError()
    fun showError(error: String)
    fun hideEmptyListError()
    fun showToastError()
    fun clearList()
}