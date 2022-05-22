package com.kay.prog.easygift.ui.authorisation

import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import com.kay.prog.easygift.extensions.toUserEntity
import com.kay.prog.easygift.ui.base.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class AuthorisationVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase
): BaseVM() {

    private val _user = MutableLiveData<UserEntity>()

    fun checkInput(nickname: String?, password: String?) {
        if (nickname.isNullOrEmpty() || password.isNullOrEmpty()) {
            _event.value = AuthEvent.OnAuthError
            return
        }

        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.size == 1) {
                        _user.value = it[0].toUserEntity()
                    } else {
                        _event.value = AuthEvent.OnAuthError
                    }
                }, {
                    handleError(it)
                })
        )

        if (_user.value == null) {
            _event.value = AuthEvent.OnAuthError
            return
        }

        if (_user.value!!.password == password) {
            _event.value = AuthEvent.OnAuthSuccess
            return
        }

        _event.value = AuthEvent.OnAuthError
    }
}