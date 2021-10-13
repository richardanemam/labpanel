package com.labpanel.feature.professor.domain.states

import com.labpanel.feature.common.domain.model.OpeningsDataModel

sealed class EmailState {
    object ValidEmail: EmailState()
    object InvalidEmail: EmailState()
}

sealed class PasswordState {
    object ValidPassword: PasswordState()
    object InvalidPassword: PasswordState()
}

sealed class NameState {
    object ValidName: NameState()
    object NullOrEmptyName: NameState()
}

sealed class OpeningDataState {
    object InvalidOpeningDataState : OpeningDataState()
    data class ValidOpeningDataState(val opening: OpeningsDataModel): OpeningDataState()
}

sealed class OpeningsState {
    object UnavailableOpenings: OpeningsState()
    data class AvailableOpenings(val data: MutableList<OpeningsDataModel>): OpeningsState()
}

sealed class AddValueEventState {
    object DataChanged: AddValueEventState()
    object Cancelled: AddValueEventState()
}