package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(user: UserDto): Observable<Unit> {
        return userRepo.createUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}