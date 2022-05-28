package com.kay.prog.easygift.data.network

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.Wish
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface BackendlessApi {

//    @GET("User")
//    fun getUsers(): Observable<List<UserDto>>

    @GET("User")
    fun getUserByNickname(
        @Query("where") where: String
    ): Single<List<UserDto>>

    @POST("User")
    fun createUser(
        @Body user: UserDto
    ): Observable<Unit>

    @PUT("User")
    fun updateUser(
        @Query("where") where: String,
        @Body user: UserDto
    ): Observable<Unit>

    @GET("Wishes")
    fun getWishesByNickname(
        @Query("where") where: String
    ): Observable<List<Wish>>

    @POST("Wishes")
    fun createWish(
        @Body wish: Wish
    ): Observable<Unit>
}