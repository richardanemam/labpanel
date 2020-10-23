package com.labpanel.presentation.view.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labpanel.domain.auth.model.UserRegistrationData
import com.labpanel.domain.auth.usecase.ProfessorRegistrationUseCase
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.NameState
import com.labpanel.presentation.view.auth.authstate.PasswordState
import com.labpanel.presentation.view.viewevents.LoadingState

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

    fun validateUserRegistrationData(userRegistrationData: UserRegistrationData) {
        useCase.validateUserRegistrationData(userRegistrationData)
    }
}