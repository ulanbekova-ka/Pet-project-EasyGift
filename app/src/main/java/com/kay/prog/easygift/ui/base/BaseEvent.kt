package com.kay.prog.easygift.ui.base

import androidx.annotation.StringRes

sealed class BaseEvent {
    class ShowToast(val message: String): BaseEvent()
}

//пример наследования
//sealed class AuthEvent: BaseEvent() {
//    object OnAuthSuccess: BaseEvent()
//    class ShowTimer(val time: Long): BaseEvent()
//}

sealed class LoadingEvent: BaseEvent() {
    class ShowToast(@StringRes val resId: Int): BaseEvent()
    object ShowLoading: BaseEvent()
    object StopLoading: BaseEvent()
}