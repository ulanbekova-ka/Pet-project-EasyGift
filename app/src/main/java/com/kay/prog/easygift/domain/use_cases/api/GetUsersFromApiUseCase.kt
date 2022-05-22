package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.kay.prog.easygift.data.repo.UserRepo
import com.kay.prog.easygift.extensions.toUserEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetUsersFromApiUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(): Observable<Unit> {
        return userRepo.getUsersFromApi()
            .subscribeOn(Schedulers.io())
            .map { response ->
                val list = mutableListOf<UserEntity>()
                response.forEach {
                    list.add(it.toUserEntity())
                }
                list.toList()
            }
            .map {
                userRepo.clearTable()
                userRepo.saveUsersToDb(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}