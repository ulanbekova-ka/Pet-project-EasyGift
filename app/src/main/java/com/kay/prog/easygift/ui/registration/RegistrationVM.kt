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

    fun saveUser(
        name: String?, surname: String?, nickname: String?, email: String?, birthday: String?, password: String?
    ) {

        if (name.isNullOrEmpty() || surname.isNullOrEmpty() || nickname.isNullOrEmpty() ||
            email.isNullOrEmpty() || birthday.isNullOrEmpty() || password.isNullOrEmpty()) {
            _event.value = RegEvent.OnEmptyFields
            return
        }

        if (!password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}\$"))) {
            _event.value = RegEvent.OnIncorrectPassword
            return
        }

        if (!birthday.matches(Regex("(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d\$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)\$)"))) {
            _event.value = RegEvent.OnIncorrectBirthdayFormat
            return
        }

        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.size == 1) {
                        _event.value = RegEvent.OnTakenNickname
                    }
                }, {
                    handleError(it)
                })
        )

        disposable.add(
            createUserUseCase(
                UserDto(birthday, null, nickname, name, surname, email, password, null)
            )
                .subscribe({
                    _event.value = RegEvent.OnRegSuccess
                }, {
                    handleError(it)
                })
        )
    }
}