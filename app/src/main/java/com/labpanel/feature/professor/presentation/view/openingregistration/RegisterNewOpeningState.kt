package com.labpanel.feature.professor.presentation.view.openingregistration

import com.labpanel.feature.app.domain.model.OpeningModel

sealed class OpeningDataState {
    object InvalidOpeningDataState : OpeningDataState()
    data class ValidOpeningDataState(val opening: OpeningModel): OpeningDataState()
}