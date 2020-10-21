package com.labpanel.presentation.view.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labpanel.domain.auth.model.UserLoginData
import com.labpanel.domain.auth.usecase.ProfessorLoginUseCase
import com.labpanel.presentation.view.auth.authstate.EmailState
import com.labpanel.presentation.view.auth.authstate.LoginState
import com.labpanel.presentation.view.auth.authstate.PasswordState
import com.labpanel.presentation.view.viewevents.LoadingState

class ProfessorLoginViewModel: ViewModel() {

    private val useCase by lazy { ProfessorLoginUseCase(emailState, passwordState) }

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    private val emailState: MutableLiveData<EmailState> = MutableLiveData()
    val onEmailState: LiveData<EmailState> = emailState

    private val passwordState: MutableLiveData<PasswordState> = MutableLiveData()
    val onPasswordState: LiveData<PasswordState> = passwordState

    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val onLoginState: LiveData<LoginState> = loginState

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