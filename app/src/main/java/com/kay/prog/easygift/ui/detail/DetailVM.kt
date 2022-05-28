package com.kay.prog.easygift.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.Relation
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.Wish
import com.kay.prog.easygift.domain.use_cases.api.FollowUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetWishesByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.db.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getWishesByNicknameUseCase: GetWishesByNicknameUseCase,
    private val followUseCase: FollowUseCase
): BaseVM() {

    fun setId(id: Long) {
        _user = getUserInfoUseCase(id) as MutableLiveData<UserEntity>

        getWishes()
    }

    private var _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity>
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

    private val logged: LiveData<UserEntity> = getUserInfoUseCase(1L)

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