package com.kay.prog.easygift.data.network

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.Wish
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BackendlessApi {

    @GET("User")
    fun getUsers(): Observable<List<UserDto>>

    @GET("User")
    fun getUserByNickname(
        @Query("where") where: String
    ): Single<List<UserDto>>

//    @POST("api/data/User")
//    fun saveUser(
//        @Body user: UserDto
//    ): Observable<Unit>
//
//    @PUT("api/data/User?where=nickname%3D'{nickname}'")
//    fun updateUser(
//        @Path("nickname") nickname: String,
//        @Body user: UserDto
//    ): Observable<Unit>



    @GET("Wishes")
    fun getWishesByNickname(
        @Query("where") where: String
    ): Observable<List<Wish>>

//    @POST("api/data/Wishes")
//    fun saveWish(
//        @Body wish: WishDto
//    ): Observable<Unit>
//
//    @PUT("api/data/Wishes?where=nickname%3D'{nickname}'")
//    fun updateWish(
//        @Path("nickname") nickname: String,
//        @Body wish: WishDto
//    ): Observable<Unit>
}