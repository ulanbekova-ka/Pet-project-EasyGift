package com.kay.prog.easygift.ui.base

import androidx.annotation.StringRes

sealed class LoadingEvent: BaseEvent() {
    class ShowToast(@StringRes val resId: Int): BaseEvent()
    object ShowLoading: BaseEvent()
    object StopLoading: BaseEvent()
}