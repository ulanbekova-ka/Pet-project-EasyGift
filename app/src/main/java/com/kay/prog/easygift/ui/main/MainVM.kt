package com.kay.prog.easygift.ui.main

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.GetUsersAsLiveUseCase
import com.kay.prog.easygift.domain.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getUsersAsLiveUseCase : GetUsersAsLiveUseCase,
    private val getUsersUseCase: GetUsersUseCase
): BaseVM() {

    val users: LiveData<List<UserEntity>> = getUsersAsLiveUseCase()

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