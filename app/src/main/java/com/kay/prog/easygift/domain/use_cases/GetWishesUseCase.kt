package com.kay.prog.easygift.domain.use_cases

import com.kay.prog.easygift.data.models.WishEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.kay.prog.easygift.data.repo.WishRepo
import com.kay.prog.easygift.extensions.toWishEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetWishesUseCase @Inject constructor(
    private val wishRepo: WishRepo
) {

    operator fun invoke(): Observable<Unit> {
        return wishRepo.getWishes()
            .subscribeOn(Schedulers.io())
            .map { response ->
                val list = mutableListOf<WishEntity>()
                response.forEach {
                    list.add(it.toWishEntity())
                }
                list.toList()
            }
            .map {
                wishRepo.clearTable()
                wishRepo.saveWishesToDb(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}