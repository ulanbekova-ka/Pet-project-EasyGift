package com.kay.prog.easygift.ui.create

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.CreateWishUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.RegEvent
import javax.inject.Inject

@HiltViewModel
class CreateWishVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val createWishUseCase: CreateWishUseCase
): BaseVM() {

    private val _user: LiveData<User> = getUserInfoUseCase(1L)
    val user: LiveData<User>
        get() = _user

    fun saveWish(description: String, url: String?, price: String?) {
        disposable.add(
            createWishUseCase(
                Wish(_user.value!!.nickname, description, url, price)
            )
                .subscribe({
                    _event.value = RegEvent.OnSuccess
                }, {
                    handleError(it)
                })
        )
    }
}