package com.kay.prog.easygift.ui.search

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.GetUsersByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val getUsersByNicknameUseCase: GetUsersByNicknameUseCase,
    private val getUsersUseCase: GetUsersUseCase
): BaseVM() {

    private var nickname: String = ""
    fun setNickname(nickname: String) {
        this.nickname = nickname

        users = getUsersByNicknameUseCase(nickname)
    }

    var users: LiveData<List<UserEntity>> = getUsersByNicknameUseCase(nickname)

    init {
        downloadUsers()
    }

    fun downloadUsers() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getUsersUseCase()
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({},{
                    handleError(it)
                })
        )
    }
}