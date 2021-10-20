package com.openinginfo.presentation.states

import com.openinginfo.domain.model.Openings

sealed class OpeningsState {
    object UnavailableOpenings: OpeningsState()
    data class AvailableOpenings(val openings: List<Openings>): OpeningsState()
}

sealed class OnBundle {
    object BundleOk : OnBundle()
    object BundleNok : OnBundle()
}