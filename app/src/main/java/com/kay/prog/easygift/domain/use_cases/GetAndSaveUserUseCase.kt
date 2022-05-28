package com.kay.prog.easygift.domain.use_cases

import com.kay.prog.easygift.data.repo.UserRepo
import com.kay.prog.easygift.extensions.toUserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAndSaveUserUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(where: String): Single<Unit> {
        return userRepo.getUserByNickname(where)
            .subscribeOn(Schedulers.io())
            .map {
                it[0].toUserEntity()
            }
            .map {
                userRepo.saveUserToDb(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}