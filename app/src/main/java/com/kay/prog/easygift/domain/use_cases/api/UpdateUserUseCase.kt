package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(where: String, user: UserDto): Observable<Unit> {
        return userRepo.updateUser(where, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}