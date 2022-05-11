package com.kay.prog.easygift.domain.use_cases

import com.kay.prog.easygift.data.models.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.kay.prog.easygift.data.repo.UserRepo
import com.kay.prog.easygift.extensions.toUserEntity
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(): Single<List<UserEntity>> {
        return userRepo.getUserFromApi()
            .subscribeOn(Schedulers.io())
            .map { list ->
                userRepo.saveUsersToDb(list.map { it.toUserEntity() })
                list.map { it.toUserEntity() }
            }
            .observeOn(AndroidSchedulers.mainThread())

    }
}