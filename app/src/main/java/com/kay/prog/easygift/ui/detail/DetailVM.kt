package com.kay.prog.easygift.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.Relation
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.FollowUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetUserByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetWishesByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserByIdUserCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserInfoUseCase
import com.kay.prog.easygift.extensions.toUserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserByIdUserCase: GetUserByIdUserCase,
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase,
    private val getWishesByNicknameUseCase: GetWishesByNicknameUseCase,
    private val followUseCase: FollowUseCase
): BaseVM() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUser(id: Long) {
        disposable.add(
            getUserByIdUserCase(id)
                .subscribe({
                    getWishes()
                }, {
                    handleError(it)
                })
        )
    }

    fun loadUser(nickname: String?) {
        disposable.add(
            getUserByNicknameUseCase("nickname='$nickname'")
                .subscribe({
                    _user.value = it[0].toUserEntity()
                    getWishes()
                }, {
                    handleError(it)
                })
        )
    }

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

    private val logged: LiveData<User> = getUserInfoUseCase(1L)

    fun follow() {
        disposable.add(
            followUseCase(
                Relation(logged.value!!.nickname, _user.value!!.nickname)
            )
                .subscribe({}, {
                    handleError(it)
                })
        )
    }
}