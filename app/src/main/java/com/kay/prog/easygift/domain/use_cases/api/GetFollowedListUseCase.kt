package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.Relation
import com.kay.prog.easygift.data.repo.UserRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetFollowedListUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(where: String): Single<List<Relation>> {
        return userRepo.getFollowedList(where)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}