package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.Relation
import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.network.BackendlessApi
import com.kay.prog.easygift.data.storage.UserDao
import javax.inject.Inject

class UserRepo @Inject constructor(
    private var userDao: UserDao,
    private var userApi: BackendlessApi
) {
    fun getUserByNickname(where: String) = userApi.getUserByNickname(where)

    fun createUser(user: UserDto) = userApi.createUser(user)

    fun updateUser(where: String, user: UserDto) = userApi.updateUser(where, user)

    fun getFollowedList(where: String) = userApi.getListOfFollowed(where)

    fun follow(relation: Relation) = userApi.follow(relation)

    fun saveUserToDb(user: User) = userDao.insertUser(user)

    fun getUsersFromDB() = userDao.getAll()

    fun getUserInfo(id: Long) = userDao.getUserInfo(id)

    fun getUserById(id : Long) = userDao.getUserById(id)

    fun deleteUsersFromDb() = userDao.deleteAll()
}