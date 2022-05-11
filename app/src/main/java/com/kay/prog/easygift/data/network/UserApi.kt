package com.kay.prog.easygift.data.network

import io.reactivex.Single
import com.kay.prog.easygift.data.models.UserDto
import retrofit2.http.GET

interface UserApi {

    @GET("example/get_users")
    fun getUsers(): Single<List<UserDto>>
}