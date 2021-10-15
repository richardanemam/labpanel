package com.presentation.states

sealed class LoadingState {
    object Hide: LoadingState()
    object Show: LoadingState()
}