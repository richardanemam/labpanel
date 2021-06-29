package com.labpanel.feature.app.domain.helper

import java.util.regex.Pattern

object EmailValidationHelper {
    private val EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")

    fun validateEmail(email: String) = EMAIL_PATTERN.matcher(email).matches()
}