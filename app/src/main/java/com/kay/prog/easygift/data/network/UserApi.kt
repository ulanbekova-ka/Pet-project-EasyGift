package com.kay.prog.easygift.data.network

import com.kay.prog.easygift.data.models.UserDto
import io.reactivex.Observable
import retrofit2.http.*

interface UserApi {

    @GET("api/data/User")
    fun getUsers(): Observable<List<UserDto>>

    @POST("api/data/User")
    fun saveUser(
        @Body user: UserDto
    ): Observable<Unit>

    @PUT("api/data/User/{objectId}")
    fun updateUser(
        @Path("objectId") objectId: String,
        @Body user: UserDto
    ): Observable<Unit>
}