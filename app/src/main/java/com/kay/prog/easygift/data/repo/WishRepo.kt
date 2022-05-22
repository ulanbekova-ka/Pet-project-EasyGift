package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.network.BackendlessApi
import javax.inject.Inject

class WishRepo @Inject constructor(
    private var wishApi: BackendlessApi
) {

    fun getWishesByNickname(nickname: String) = wishApi.getWishesByNickname(nickname)

    fun getUserByNickname(where: String) = wishApi.getUserByNickname(where)
}