package com.domain.extensions

import com.core.Regex
import java.util.*

fun String?.isValid(): Boolean = !(this.isNullOrEmpty() || this.isBlank())
fun String.isAValidEmail(): Boolean = Regex.EMAIL_PATTERN.matcher(this).matches()
fun String.setAsChild() = Regex.ALPHANUMERIC_PATTERN.replace(this, "").toLowerCase(Locale.ROOT)
fun String.isAValidProfessorUfabcEmail() = Regex.PROFESSOR_EMAIL_PATTERN.matcher(this).matches()