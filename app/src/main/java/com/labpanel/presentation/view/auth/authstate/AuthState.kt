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