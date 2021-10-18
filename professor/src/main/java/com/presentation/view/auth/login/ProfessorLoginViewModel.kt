package com.presentation.view.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.UserLoginData
import com.domain.usecase.ProfessorLoginUseCase
import com.presentation.states.EmailState
import com.presentation.states.LoadingState
import com.presentation.states.PasswordState

class ProfessorLoginViewModel: ViewModel() {

    private val useCase by lazy { ProfessorLoginUseCase(emailState, passwordState) }

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    private val emailState: MutableLiveData<EmailState> = MutableLiveData()
    val onEmailState: LiveData<EmailState> = emailState

    private val passwordState: MutableLiveData<PasswordState> = MutableLiveData()
    val onPasswordState: LiveData<PasswordState> = passwordState

    fun showLoading() {
        loadingState.value = LoadingState.Show
    }

    fun hideLoading() {
        loadingState.value = LoadingState.Hide
    }

    fun validateUserLoginData(userLoginData: UserLoginData) {
        useCase.validateUserLoginData(userLoginData)
    }
}