package com.kay.prog.easygift.ui.mylist

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.db.GetUsersFromDbUseCase
import com.kay.prog.easygift.domain.use_cases.api.GetUsersFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import javax.inject.Inject

@HiltViewModel
class MylistVM @Inject constructor(
    private val getUsersFromDbUseCase : GetUsersFromDbUseCase,
    private val getUsersFromApiUseCase: GetUsersFromApiUseCase
): BaseVM() {

    val users: LiveData<List<UserEntity>> = getUsersFromDbUseCase()

    init {
        downloadUsers()
    }

    fun downloadUsers() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getUsersFromApiUseCase()
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({},{
                    handleError(it)
                })
        )
    }
}