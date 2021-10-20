package com.presentation.states

import com.data.model.Opening
import com.openinginfo.domain.model.Openings

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
    data class ValidOpeningDataState(val opening: Opening): OpeningDataState()
}

sealed class AddOpeningValueState {
    object DataChanged: AddOpeningValueState()
    object Cancelled: AddOpeningValueState()
}

sealed class ProfessorOpeningsState {
    object UnavailableOpenings: ProfessorOpeningsState()
    data class AvailableOpenings(val professorOpenings: List<Openings>): ProfessorOpeningsState()
}