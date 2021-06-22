package com.labpanel.presentation.view.openingregistration

import com.labpanel.domain.auth.model.NewOpeningRegistrationModel

sealed class OpeningDataState {
    object InvalidOpeningDataState : OpeningDataState()
    data class ValidOpeningDataState(val opening: NewOpeningRegistrationModel): OpeningDataState()
}