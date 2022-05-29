package com.kay.prog.easygift.ui.registration

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.domain.use_cases.api.CreateUserUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.RegEvent
import javax.inject.Inject

@HiltViewModel
class RegistrationVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase,
    private val createUserUseCase: CreateUserUseCase
): BaseVM() {

    fun checkNickname(name: String, surname: String, nickname: String, email: String, birthday: String, password: String) {
        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.size == 1) {
                        _event.value = RegEvent.OnTakenNickname
                    } else {
                        postUser(name, surname, nickname, email, birthday, password)
                    }
                }, {
                    handleError(it)
                })
        )
    }

    private fun postUser(name: String, surname: String, nickname: String, email: String, birthday: String, password: String) {
        disposable.add(
            createUserUseCase(
                UserDto(birthday, null, nickname, name, surname, email, password)
            )
                .subscribe({
                    _event.value = RegEvent.OnSuccess
                }, {
                    handleError(it)
                })
        )
    }
}