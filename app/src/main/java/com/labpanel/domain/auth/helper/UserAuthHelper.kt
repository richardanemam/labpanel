package com.labpanel.domain.auth.helper

import android.util.Patterns

object UserAuthHelper {

    private const val PASSWORD_MINIMUM_LENGTH = 5

    fun validateEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun validatePassword(password: String) = password.length.isValid()
    private fun Int.isValid() = this > PASSWORD_MINIMUM_LENGTH
}