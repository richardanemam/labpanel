package com.labpanel.presentation.view.auth.authstate

sealed class LoginState {
    object LoginAuthorized: LoginState()
    object LoginFailed: LoginState()
}