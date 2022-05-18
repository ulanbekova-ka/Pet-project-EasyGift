package com.kay.prog.easygift.ui.main

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.R
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.use_cases.GetUsersAsLiveUseCase
import com.kay.prog.easygift.domain.use_cases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kay.prog.easygift.ui.base.BaseVM
import com.kay.prog.easygift.ui.base.LoadingEvent
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getUsersAsLiveUseCase : GetUsersAsLiveUseCase,
    private val getUsersUseCase: GetUsersUseCase
): BaseVM() {

    val users: LiveData<List<UserEntity>> = getUsersAsLiveUseCase()

    init {
        getUsers()
    }

    fun getUsers() {
        _event.value = LoadingEvent.ShowLoading

        disposable.add(
            getUsersUseCase()
                .doOnTerminate { _event.value = LoadingEvent.StopLoading }
                .subscribe({},{
                    handleError(it)
                })
        )
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> LoadingEvent.ShowToast(R.string.no_internet)
            else -> LoadingEvent.ShowToast(R.string.unknown_error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        clearEvents()
    }

    private fun clearEvents() {
        _event.value = null
    }
}