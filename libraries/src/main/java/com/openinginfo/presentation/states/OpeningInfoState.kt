package com.openinginfo.presentation.states

import com.openinginfo.domain.model.OpeningsDataModel

sealed class OpeningsState {
    object UnavailableOpenings: OpeningsState()
    data class AvailableOpenings(val data: MutableList<OpeningsDataModel>): OpeningsState()
}

sealed class OnBundle {
    object BundleOk : OnBundle()
    object BundleNok : OnBundle()
}