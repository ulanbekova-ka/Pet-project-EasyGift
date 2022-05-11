package com.kay.prog.easygift.ui.base

sealed class BaseEvent {
    class ShowToast(val message: String): BaseEvent()
}

//пример наследования
sealed class AuthEvent: BaseEvent(){
    object OnAuthSuccess: BaseEvent()
    class ShowTimer(val time: Long): BaseEvent()
}