package com.kay.prog.easygift.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
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

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}