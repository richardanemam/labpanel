package com.labpanel.domain.auth.usecase

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.labpanel.domain.auth.model.UserLoginData
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.PasswordState

class ProfessorLoginUseCase(
    private val emailState: MutableLiveData<EmailState>,
    private val passwordState: MutableLiveData<PasswordState>) {

    companion object {
        private const val PASSWORD_MINIMUM_LENGTH = 5
    }

    fun validateUserLoginData(userLoginData: UserLoginData) {
        validateEmail(userLoginData.email)
        validatePassword(userLoginData.password)
    }

    private fun validateEmail(email: String) {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailState.value = EmailState.ValidEmail
        } else {
            emailState.value = EmailState.InvalidEmail
        }
    }

    private fun validatePassword(password: String) {
        if(password.length.isValid()) {
            passwordState.value = PasswordState.ValidPassword
        } else {
            passwordState.value = PasswordState.InvalidPassword
        }
    }

    private fun Int.isValid() = this > PASSWORD_MINIMUM_LENGTH
}