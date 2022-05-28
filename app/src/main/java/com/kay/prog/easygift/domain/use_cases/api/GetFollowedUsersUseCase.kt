package com.kay.prog.easygift.domain.use_cases.api

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.kay.prog.easygift.data.repo.UserRepo
import com.kay.prog.easygift.extensions.toUserEntity
import javax.inject.Inject

class GetFollowedUsersUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    @SuppressLint("CheckResult")
    operator fun invoke(nicknames: List<String>?) {

        nicknames?.forEach { nickname ->
            userRepo.getUserByNickname("nickname='$nickname'")
                .subscribeOn(Schedulers.io())
                .map {
                    if (it.size == 1) {
                        userRepo.saveUserToDb(it[0].toUserEntity())
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
        }

    }
}