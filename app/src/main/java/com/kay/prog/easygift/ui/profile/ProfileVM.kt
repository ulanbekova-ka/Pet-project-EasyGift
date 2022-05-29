package com.kay.prog.easygift.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.GetWishesByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.db.ClearTableUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getWishesByNicknameUseCase: GetWishesByNicknameUseCase,
    private val clearTableUseCase: ClearTableUseCase
): BaseVM() {

    private val _user: LiveData<User> = getUserInfoUseCase(1L)
    val user: LiveData<User>
        get() = _user

    private val _wishList = MutableLiveData<List<Wish>>()
    val wishList: LiveData<List<Wish>>
        get() = _wishList

    fun getWishes() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getWishesByNicknameUseCase("nickname='${_user.value!!.nickname}'")
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