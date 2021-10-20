package com.domain.extensions

import com.core.Password

fun Int.isAValidPasswordLength() = this > Password.PASSWORD_MINIMUM_LENGTH