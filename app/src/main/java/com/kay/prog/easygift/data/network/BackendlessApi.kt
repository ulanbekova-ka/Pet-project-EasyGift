package com.kay.prog.easygift.data.network

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.WishDto
import io.reactivex.Observable
import retrofit2.http.*

interface BackendlessApi {

    @GET("api/data/User")
    fun getUsers(): Observable<List<UserDto>>

//    @GET("api/data/User/{objectId}")
//    fun getUserInfo(
//        @Path("objectId") objectId: String
//    ): LiveData<UserDto>

//    @POST("api/data/User")
//    fun saveUser(
//        @Body user: UserDto
//    ): Observable<Unit>
//
//    @PUT("api/data/User/{objectId}")
//    fun updateUser(
//        @Path("objectId") objectId: String,
//        @Body user: UserDto
//    ): Observable<Unit>

    @GET("api/data/Wishes")
    fun getWishes(): Observable<List<WishDto>>

//    @POST("api/data/User")
//    fun saveWish(
//        @Body wish: Wish
//    ): Observable<Unit>
//
//    @PUT("api/data/User/{objectId}")
//    fun updateWish(
//        @Path("objectId") objectId: String,
//        @Body wish: Wish
//    ): Observable<Unit>
}