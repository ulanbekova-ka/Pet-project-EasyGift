package com.kay.prog.easygift.ui.create

import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.CreateWishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.RegEvent
import javax.inject.Inject

@HiltViewModel
class CreateWishVM @Inject constructor(
    private val createWishUseCase: CreateWishUseCase
): BaseVM() {

    //TODO - take by id
    private var nickname: String = ""
    fun setNickname(nickname: String?) {
        this.nickname = nickname ?: ""
    }

    //TODO not working
    fun saveWish(description: String, url: String?, price: Int?) {
        disposable.add(
            createWishUseCase(
                Wish(nickname, description, url, price)
            )
                .subscribe({
                    _event.value = RegEvent.OnSuccess
                }, {
                    handleError(it)
                })
        )
    }
}