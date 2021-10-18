package com.domain.model

data class UserLoginData(val email: String, val password: String)

data class UserRegistrationData(val name: String, val email: String, val password: String)