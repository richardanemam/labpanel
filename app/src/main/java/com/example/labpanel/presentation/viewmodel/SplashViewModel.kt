package com.example.labpanel.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    val mutableLiveData: MutableLiveData<SplashState> = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch {
            delay(Long.MAX_VALUE)
            mutableLiveData.postValue(SplashState.DashboardActivity)
        }
    }

    sealed class SplashState{
        object DashboardActivity : SplashState()
    }
}