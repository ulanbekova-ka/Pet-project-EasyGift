package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.network.BackendlessApi
import com.kay.prog.easygift.data.storage.UserDao
import javax.inject.Inject

class UserRepo @Inject constructor(
    private var userDao: UserDao,
    private var userApi: BackendlessApi
) {

    fun getUsersFromApi() = userApi.getUsers()

    fun getUserById(id : Long) = userDao.getUserById(id)

    fun saveUsersToDb(users: List<UserEntity>) {
        userDao.insertList(users)
    }

    fun getUsersByNickname(nickname: String) = userDao.getUsersByNickname(nickname)

    fun clearTable() {
        userDao.clearTable()
    }

    fun getUsersFromDB() = userDao.getAll()
}