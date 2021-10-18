package com.presentation.states

import com.openinginfo.domain.model.OpeningsDataModel

sealed class LoadingState {
    object Hide: LoadingState()
    object Show: LoadingState()
}

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

sealed class AddValueEventState {
    object DataChanged: AddValueEventState()
    object Cancelled: AddValueEventState()
}