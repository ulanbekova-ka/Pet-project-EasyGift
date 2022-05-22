package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.repo.WishRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetUserByNicknameUseCase @Inject constructor(
    private val wishRepo: WishRepo
) {

    operator fun invoke(where: String): Single<UserDto> {
        return wishRepo.getUserByNickname(where)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}