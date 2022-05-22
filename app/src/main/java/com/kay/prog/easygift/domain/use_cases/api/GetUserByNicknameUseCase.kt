package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetUserByNicknameUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(where: String): Single<List<UserDto>> {
        return userRepo.getUserByNickname(where)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}