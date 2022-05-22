package com.kay.prog.easygift.ui.base

import androidx.annotation.StringRes

sealed class BaseEvent {
    class ShowToast(val message: String): BaseEvent()
}

sealed class AuthEvent: BaseEvent() {
    object OnAuthSuccess: BaseEvent()
    object OnAuthError: BaseEvent()
}

sealed class RegEvent: BaseEvent() {
    object OnEmptyFields: BaseEvent()
    object OnIncorrectPassword: BaseEvent()
    object OnIncorrectBirthdayFormat: BaseEvent()
    object OnTakenNickname: BaseEvent()
    object OnUnknownError: BaseEvent()
    object OnRegSuccess: BaseEvent()
}

sealed class LoadingEvent: BaseEvent() {
    class ShowToast(@StringRes val resId: Int): BaseEvent()
    object ShowLoading: BaseEvent()
    object StopLoading: BaseEvent()
}