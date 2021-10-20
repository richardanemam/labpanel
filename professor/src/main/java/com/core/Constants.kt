package com.core

import java.util.regex.Pattern

object FirebaseResponse {
    const val ON_DATA_CHANGED = 1
    const val ON_CANCELED = -1
}

object Regex {
    val EMAIL_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
    val ALPHANUMERIC_PATTERN = Pattern.compile("[^a-zA-Z0-9]").toRegex()
    val PROFESSOR_EMAIL_PATTERN: Pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@ufabc.edu.br\$")
}

object Password {
    const val PASSWORD_MINIMUM_LENGTH = 5
}