package com.kay.prog.easygift.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase
): BaseVM() {

    private val _users = MutableLiveData<List<UserDto>>()
    val users: LiveData<List<UserDto>>
        get() = _users

    fun findUser(nickname: String?) {
        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.isNotEmpty()) {
                        _users.value = it
                    }
                }, {
                    handleError(it)
                })
        )
    }
}