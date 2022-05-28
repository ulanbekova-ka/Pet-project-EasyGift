package com.kay.prog.easygift.ui.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.db.GetUsersFromDbUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetFollowedUsersUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class MylistVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase,
    private val getUsersFromDbUseCase : GetUsersFromDbUseCase,
    private val getFollowedUsersUseCase: GetFollowedUsersUseCase
): BaseVM() {

    private var nickname: String = ""
    fun setNickname(nickname: String?) {
        this.nickname = nickname ?: ""

        getUser()
    }

    private val _user = MutableLiveData<UserDto>()
    val user: LiveData<UserDto>
        get() = _user

    val users: LiveData<List<UserEntity>> = getUsersFromDbUseCase()

    fun downloadUsers() {
        _event.value = LoadingEvent.ShowLoading

        getFollowedUsersUseCase(_user.value!!.followed)
        _event.value = LoadingEvent.StopLoading
    }

    private fun getUser() {
        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.size == 1) {
                        _user.value = it[0]
                    }
                },{
                    handleError(it)
                })
        )
    }
}