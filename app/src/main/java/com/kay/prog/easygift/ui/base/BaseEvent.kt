package com.kay.prog.easygift.ui.base

sealed class BaseEvent {
    class ShowToast(val message: String): BaseEvent()
}

sealed class AuthEvent: BaseEvent() {
    object OnAuthSuccess: BaseEvent()
    object OnAuthError: BaseEvent()
}