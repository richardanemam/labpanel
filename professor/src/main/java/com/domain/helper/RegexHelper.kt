package com.domain.helper

import java.util.*
import java.util.regex.Pattern

object RegexHelper {
    private val EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
    private val ALPHANUMERIC_PATTERN = Pattern.compile("[^a-zA-Z0-9]").toRegex()

    fun validateEmail(email: String) = EMAIL_PATTERN.matcher(email).matches()
    fun setTitleAsChild(title: String) =
        ALPHANUMERIC_PATTERN.replace(title, "").toLowerCase(Locale.ROOT)
}