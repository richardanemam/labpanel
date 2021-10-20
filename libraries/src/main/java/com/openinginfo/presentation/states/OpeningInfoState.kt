package com.openinginfo.presentation.states

import com.openinginfo.domain.model.OpeningVO

sealed class OpeningsState {
    object UnavailableOpenings: OpeningsState()
    data class AvailableOpenings(val data: MutableList<OpeningVO>): OpeningsState()
}

sealed class OnBundle {
    object BundleOk : OnBundle()
    object BundleNok : OnBundle()
}