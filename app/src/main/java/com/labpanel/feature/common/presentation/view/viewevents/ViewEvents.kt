package com.labpanel.feature.common.presentation.view.viewevents

sealed class LoadingState {
    object Hide: LoadingState()
    object Show: LoadingState()
}

sealed class OnBundle {
    object BundleOk : OnBundle()
    object BundleNok : OnBundle()
}