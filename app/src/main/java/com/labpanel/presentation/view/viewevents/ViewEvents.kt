package com.labpanel.presentation.view.viewevents

sealed class LoadingState {
    object Hide: LoadingState()
    object Show: LoadingState()
}