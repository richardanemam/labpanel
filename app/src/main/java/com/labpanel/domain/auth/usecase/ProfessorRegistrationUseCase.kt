package com.labpanel.domain.auth.usecase

import androidx.lifecycle.MutableLiveData
import com.labpanel.domain.auth.helper.UserAuthHelper
import com.labpanel.domain.auth.model.UserRegistrationData
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.NameState
import com.labpanel.presentation.view.auth.authstate.PasswordState

class ProfessorRegistrationUseCase(private val  nameState: MutableLiveData<NameState>,
    private val emailState: MutableLiveData<EmailState>,
    private val passwordState: MutableLiveData<PasswordState>) {

    fun validateUserRegistrationData(userRegistrationData: UserRegistrationData) {
        validateName(userRegistrationData.name)
        validateEmail(userRegistrationData.email)
        validatePassword(userRegistrationData.password)
    }

    private fun validateName(name: String) {
        if(name.isEmpty()) {
            nameState.value = NameState.NullOrEmptyName
        } else {
            nameState.value = NameState.ValidName
        }
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