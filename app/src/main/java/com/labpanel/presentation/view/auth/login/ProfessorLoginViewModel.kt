package com.labpanel.presentation.view.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labpanel.presentation.view.auth.authstate.LoginState
import com.labpanel.presentation.view.viewevents.LoadingState

class ProfessorLoginViewModel: ViewModel() {

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val onLoginState: LiveData<LoginState> = loginState

    fun showLoading() {
        loadingState.value = LoadingState.Show
    }

}