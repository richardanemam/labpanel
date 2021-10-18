package com.domain.helper

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

object UserAuthHelper {

    private const val PASSWORD_MINIMUM_LENGTH = 5
    private val PROFESSOR_EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@ufabc.edu.br\$")

    fun getFirebaseAuth() = Firebase.auth
    fun validateEmail(email: String) = PROFESSOR_EMAIL_PATTERN.matcher(email).matches()
    fun validatePassword(password: String) = password.length.isValid()
    private fun Int.isValid() = this > PASSWORD_MINIMUM_LENGTH
}