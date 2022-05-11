package com.kay.prog.easygift.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.domain.models.User
import com.kay.prog.easygift.domain.use_cases.GetUserUseCase
import com.kay.prog.easygift.ui.base.BaseEvent
import com.kay.prog.easygift.ui.base.BaseVM
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): BaseVM() {

    private val _user =  MutableLiveData<List<User>>()
    val user: LiveData<List<User>>
        get() = _user

    fun getUser(){
        disposable.add(
            getUserUseCase()
                .subscribe({
                    _user.value = it
                },{
                    _event.value = BaseEvent.ShowToast(it.message ?: "")
                })
        )
    }
}