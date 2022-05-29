package com.kay.prog.easygift.ui.base

import androidx.annotation.StringRes

sealed class BaseEvent {
    class ShowToast(@StringRes val resId: Int): BaseEvent()
}

sealed class AuthEvent: BaseEvent() {
    object OnSuccess: BaseEvent()
    object OnUserNotFound: BaseEvent()
    object OnWrongPassword: BaseEvent()
}

sealed class RegEvent: BaseEvent() {
    object OnTakenNickname: BaseEvent()
    object OnSuccess: BaseEvent()
}

sealed class LoadingEvent: BaseEvent() {
    object ShowLoading: BaseEvent()
    object StopLoading: BaseEvent()
}