package com.kay.prog.easygift.domain.use_cases.api

import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.data.repo.WishRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateWishUseCase @Inject constructor(
    private val wishRepo: WishRepo
) {

    operator fun invoke(wish: Wish): Observable<Unit> {
        return wishRepo.createWish(wish)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}