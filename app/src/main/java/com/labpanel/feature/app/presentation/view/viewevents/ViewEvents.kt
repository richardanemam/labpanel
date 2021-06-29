package com.labpanel.feature.app.presentation.view.viewevents

sealed class LoadingState {
    object Hide: LoadingState()
    object Show: LoadingState()
}