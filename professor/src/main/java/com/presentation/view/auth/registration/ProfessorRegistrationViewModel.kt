package com.presentation.view.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.UserRegistration
import com.domain.usecase.ProfessorRegistrationUseCase
import com.presentation.states.EmailState
import com.presentation.states.LoadingState
import com.presentation.states.NameState
import com.presentation.states.PasswordState

class ProfessorRegistrationViewModel: ViewModel() {

    private val useCase by lazy {
        ProfessorRegistrationUseCase(nameState, emailState, passwordState)
    }

    private val nameState: MutableLiveData<NameState> = MutableLiveData()
    val onNameState: LiveData<NameState> = nameState

    private val emailState: MutableLiveData<EmailState> = MutableLiveData()
    val onEmailState: LiveData<EmailState> = emailState

    private val passwordState: MutableLiveData<PasswordState> = MutableLiveData()
    val onPasswordState: LiveData<PasswordState> = passwordState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun showLoading() {
        loadingState.value = LoadingState.Show
    }

    fun hideLoading() {
        loadingState.value = LoadingState.Hide
    }

    fun validateUserRegistrationData(userRegistration: UserRegistration) {
        useCase.validateUserRegistrationData(userRegistration)
    }
}