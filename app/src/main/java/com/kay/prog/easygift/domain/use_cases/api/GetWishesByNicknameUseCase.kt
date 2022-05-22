package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.data.repo.WishRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetWishesByNicknameUseCase @Inject constructor(
    private val wishRepo: WishRepo
) {

    operator fun invoke(nickname: String): Observable<List<Wish>> {
        return wishRepo.getWishesByNickname(nickname)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}