package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.network.UserApi
import com.kay.prog.easygift.data.storage.UserDao
import javax.inject.Inject

class UserRepo @Inject constructor(
    private var userDao: UserDao,
    private var userApi: UserApi
) {

    fun getUserFromApi() = userApi.getUsers()

    fun saveUsersToDb(users: List<UserEntity>) = userDao.insertUsers(users)
}