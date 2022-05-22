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

    private var nickname: String = ""
    fun setNickname(nickname: String?) {
        this.nickname = nickname ?: ""
    }

    fun saveWish(description: String?, url: String?, price: Double?) {
        if (description.isNullOrEmpty()) {
            _event.value = RegEvent.OnEmptyFields
            return
        }

        disposable.add(
            createWishUseCase( Wish(null, nickname, description, url, price) )
                .subscribe({
                    _event.value = RegEvent.OnRegSuccess
                }, {
                    handleError(it)
                })
        )
    }
}