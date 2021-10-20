package com.domain.helper

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

object UserAuthHelper {
    fun getFirebaseAuth() = Firebase.auth
}