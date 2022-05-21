package com.kay.prog.easygift.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kay.prog.easygift.R
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
open class BaseVM @Inject constructor() : ViewModel() {

    protected val disposable by lazy {
        CompositeDisposable()
    }

    protected val _event by lazy {
        MutableLiveData<BaseEvent>()
    }

    val event: LiveData<BaseEvent>
        get() = _event

//    private val _isLoading by lazy {
//        MutableLiveData(false)
//    }
//    val isLoading: LiveData<Boolean>
//        get() = _isLoading
//
//
//    fun showLoading(){
//        _isLoading.value = true
//    }
//
//    protected fun hideLoading(){
//        _isLoading.value = false
//    }

    protected fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> LoadingEvent.ShowToast(R.string.no_internet)
            else -> LoadingEvent.ShowToast(R.string.unknown_error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        _event.value = null
    }
}