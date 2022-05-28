package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.network.BackendlessApi
import com.kay.prog.easygift.data.storage.UserDao
import javax.inject.Inject

class UserRepo @Inject constructor(
    private var userDao: UserDao,
    private var userApi: BackendlessApi
) {

//    fun getUsersFromApi() = userApi.getUsers()

    fun getUserByNickname(where: String) = userApi.getUserByNickname(where)

    fun createUser(user: UserDto) = userApi.createUser(user)

    fun updateUser(where: String, user: UserDto) = userApi.updateUser(where, user)



    fun saveUserToDb(user: UserEntity) = userDao.insert(user)
//    fun saveUsersToDb(users: List<UserEntity>) = userDao.insertList(users)

    fun getUsersFromDB() = userDao.getAll()

    fun getUserById(id : Long) = userDao.getUserById(id)
}