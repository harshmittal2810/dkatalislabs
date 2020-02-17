package com.harsh.dkatalislabs.model.services

import com.harsh.dkatalislabs.model.entities.UserListModel
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("0.4/?randomapi")
    fun getRandomPerson(): Call<UserListModel>
}

