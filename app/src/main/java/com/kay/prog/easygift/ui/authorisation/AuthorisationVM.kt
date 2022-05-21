package com.kay.prog.easygift.ui.authorisation

import com.kay.prog.easygift.domain.use_cases.GetUsersUseCase
import com.kay.prog.easygift.ui.base.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class AuthorisationVM @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): BaseVM() {
    lateinit var objectId: String

    fun checkInput(nickname: String?, password: String?) {
        if (nickname.isNullOrEmpty() || password.isNullOrEmpty()) {
            _event.value = AuthEvent.OnAuthError
        }

        //TODO
    }
}