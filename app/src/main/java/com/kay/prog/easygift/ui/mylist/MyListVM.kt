package com.kay.prog.easygift.ui.mylist

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.GetAndSaveUserUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetFollowedListUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUsersFromDbUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class MyListVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAndSaveUserUseCase: GetAndSaveUserUseCase,
    private val getFollowedListUseCase: GetFollowedListUseCase,
    private val getUsersFromDbUseCase : GetUsersFromDbUseCase
): BaseVM() {

    private val _user: LiveData<UserEntity> = getUserInfoUseCase(1L)
    val user: LiveData<UserEntity>
        get() = _user

    val users: LiveData<MutableList<UserEntity>> = getUsersFromDbUseCase()

    fun downloadUsers() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getFollowedListUseCase("nickname='${_user.value?.nickname}'")
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({ list ->
                    list.forEach {
                        saveUser(it.follows)
                    }
                }, {
                    handleError(it)
                })
        )
    }

    private fun saveUser(nickname: String) {
        getAndSaveUserUseCase("nickname='$nickname'")
    }
}