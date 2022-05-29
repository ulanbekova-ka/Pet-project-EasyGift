package com.kay.prog.easygift.domain.use_cases.db

import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetUserByIdUserCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(id: Long): Single<User> {
        return userRepo.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}