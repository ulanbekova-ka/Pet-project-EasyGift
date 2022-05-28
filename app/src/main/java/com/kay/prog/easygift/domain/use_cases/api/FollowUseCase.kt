package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.Relation
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(relation: Relation): Observable<Unit> {
        return userRepo.follow(relation)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}