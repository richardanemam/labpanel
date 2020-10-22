package com.labpanel.presentation.view.auth.authstate

sealed class LoginState {
    object LoginAuthorized: LoginState()
    object LoginFailed: LoginState()
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

sealed class CurrentUserState {
    object User: CurrentUserState()
    object NotAUser: CurrentUserState()
}