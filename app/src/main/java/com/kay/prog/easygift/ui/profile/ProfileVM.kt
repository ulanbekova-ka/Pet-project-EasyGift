package com.kay.prog.easygift.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.GetWishesByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.db.ClearTableUseCase
import com.kay.prog.easygift.extensions.toUserEntity
import com.kay.prog.easygift.ui.base.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase,
    private val getWishesByNicknameUseCase: GetWishesByNicknameUseCase,
    private val clearTableUseCase: ClearTableUseCase
): BaseVM() {

    // TODO delete fun
    private var nickname: String = ""
    fun setNickname(nickname: String?) {
        this.nickname = nickname ?: ""

        getUser()
        getWishes()
    }

    // TODO get User by Id from DB - LiveData
    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity>
        get() = _user

    private val _wishList = MutableLiveData<List<Wish>>()
    val wishList: LiveData<List<Wish>>
        get() = _wishList

    // TODO delete fun
    fun getUser() {
        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    if (it.size == 1) {
                        _user.value = it[0].toUserEntity()
                    } else {
                        _event.value = AuthEvent.OnUserNotFound
                    }
                },{
                    handleError(it)
                })
        )
    }

    fun getWishes() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getWishesByNicknameUseCase("nickname='$nickname'")
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({
                    _wishList.value = it
                },{
                    handleError(it)
                })
        )
    }

    fun deleteUsers() {
        disposable.add(
            clearTableUseCase()
                .subscribe ({}, {
                    handleError(it)
                })
        )
    }
}