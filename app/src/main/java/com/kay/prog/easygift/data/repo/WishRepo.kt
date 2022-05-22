package com.kay.prog.easygift.data.repo

import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.data.network.BackendlessApi
import javax.inject.Inject

class WishRepo @Inject constructor(
    private var wishApi: BackendlessApi
) {

    fun getWishesByNickname(nickname: String) = wishApi.getWishesByNickname(nickname)

    fun createWish(wish: Wish) = wishApi.createWish(wish)
}