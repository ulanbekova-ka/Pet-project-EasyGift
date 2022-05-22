package com.kay.prog.easygift.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import com.kay.prog.easygift.extensions.toUserEntity
import com.kay.prog.easygift.ui.base.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase
): BaseVM() {

    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity>
        get() = _user

    fun findUser(nickname: String?) {
        if (nickname.isNullOrEmpty()) {
            _event.value = AuthEvent.OnAuthError
            return
        }

        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    _user.value = it.toUserEntity()
                }, {
                    _event.value = AuthEvent.OnAuthError
                })
        )

        if (_user.value != null) {
            _event.value = AuthEvent.OnAuthSuccess
            return
        }

        _event.value = AuthEvent.OnAuthError
    }
}