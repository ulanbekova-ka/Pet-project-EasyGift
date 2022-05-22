package com.kay.prog.easygift.ui.create

import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.RegEvent
import javax.inject.Inject

@HiltViewModel
class CreateWishVM @Inject constructor(
//    private val getUserByNicknameUseCase: GetUserByNicknameUseCase
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

        // TODO save wish! _event.value = RegEvent.OnRegSuccess
//        disposable.add(
//
//        )

        _event.value = RegEvent.OnUnknownError
    }
}