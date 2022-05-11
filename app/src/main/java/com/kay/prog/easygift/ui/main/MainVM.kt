package com.kay.prog.easygift.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.easygift.data.models.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.domain.use_cases.GetUserUseCase
import com.kay.prog.easygift.ui.base.BaseEvent
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): BaseVM() {

    private val _users =  MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>>
        get() = _users

    fun getUsers(){
        disposable.add(
            getUserUseCase()
                .subscribe({
                    _users.value = it
                },{
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }

    fun getUserByIndex(index: Int): UserEntity {
        return users.value?.get(index) as UserEntity
    }
}