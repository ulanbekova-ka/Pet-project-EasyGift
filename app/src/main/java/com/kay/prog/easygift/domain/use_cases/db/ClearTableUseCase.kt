package com.kay.prog.easygift.domain.use_cases.db

import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClearTableUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(): Maybe<Unit> {
        return Maybe.fromAction<Unit>(userRepo::deleteUsersFromDb)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}