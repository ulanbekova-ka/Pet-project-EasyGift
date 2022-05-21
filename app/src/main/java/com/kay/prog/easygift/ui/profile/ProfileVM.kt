package com.kay.prog.easygift.ui.profile

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.WishEntity
import com.kay.prog.easygift.domain.use_cases.GetUserInfoUseCase
import com.kay.prog.easygift.domain.use_cases.GetWishesByNicknameUseCase
import com.kay.prog.easygift.domain.use_cases.GetWishesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getWishesUseCase: GetWishesUseCase,
    private val getWishesByNicknameUseCase: GetWishesByNicknameUseCase
): BaseVM() {

    private var id: Long = 1
    fun setId(id: Long) {
        this.id = id

        user = getUserInfoUseCase(id)
    }

    private var nickname: String = "emil01"
    fun setNickname(nickname: String) {
        this.nickname = nickname

        //TODO problem with wish list - crashes when looking at app inspector
//        wishList = getWishesByNicknameUseCase(nickname)
    }

    var user: LiveData<UserEntity> = getUserInfoUseCase(id)
    var wishList: LiveData<List<WishEntity>> = getWishesByNicknameUseCase(nickname)

    init {
        getWishes()
    }

    fun getWishes() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getWishesUseCase()
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({},{
                    handleError(it)
                })
        )
    }
}