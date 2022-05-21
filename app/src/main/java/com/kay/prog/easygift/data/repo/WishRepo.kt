package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.WishEntity
import com.kay.prog.easygift.data.network.BackendlessApi
import com.kay.prog.easygift.data.storage.WishDao
import javax.inject.Inject

class WishRepo @Inject constructor(
    private var wishDao: WishDao,
    private var wishApi: BackendlessApi
) {

    fun getWishes() = wishApi.getWishes()

    fun getWishByNickname(nickname: String) = wishDao.getWishByNickname(nickname)

    fun saveWishesToDb(wishes: List<WishEntity>) {
        wishDao.insertList(wishes)
    }

    fun clearTable() {
        wishDao.clearTable()
    }
}