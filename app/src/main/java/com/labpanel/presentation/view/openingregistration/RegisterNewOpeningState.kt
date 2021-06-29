package com.labpanel.presentation.view.openingregistration

import com.labpanel.domain.model.OpeningModel

sealed class OpeningDataState {
    object InvalidOpeningDataState : OpeningDataState()
    data class ValidOpeningDataState(val opening: OpeningModel): OpeningDataState()
}