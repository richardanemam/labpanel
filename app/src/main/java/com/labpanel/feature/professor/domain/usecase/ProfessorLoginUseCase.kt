package com.labpanel.feature.professor.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.labpanel.feature.professor.domain.helper.UserAuthHelper
import com.labpanel.feature.professor.domain.model.UserLoginData
import com.labpanel.feature.professor.presentation.view.auth.authstate.EmailState
import com.labpanel.feature.professor.presentation.view.auth.authstate.PasswordState

class ProfessorLoginUseCase(
    private val emailState: MutableLiveData<EmailState>,
    private val passwordState: MutableLiveData<PasswordState>) {

    fun validateUserLoginData(userLoginData: UserLoginData) {
        validateEmail(userLoginData.email)
        validatePassword(userLoginData.password)
    }

    private fun validateEmail(email: String) {
        if(UserAuthHelper.validateEmail(email)) {
            emailState.value = EmailState.ValidEmail
        } else {
            emailState.value = EmailState.InvalidEmail
        }
    }

    private fun validatePassword(password: String) {
        if(UserAuthHelper.validatePassword(password)) {
            passwordState.value = PasswordState.ValidPassword
        } else {
            passwordState.value = PasswordState.InvalidPassword
        }
    }
}