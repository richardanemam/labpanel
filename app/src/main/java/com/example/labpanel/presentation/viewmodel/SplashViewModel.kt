package com.example.labpanel.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    companion object {
        private const val MILI_SECONDS_TIME = 3_000L
    }

    val mutableLiveData: MutableLiveData<SplashState> = MutableLiveData()

    init {
        GlobalScope.launch {
            delay(MILI_SECONDS_TIME)
            mutableLiveData.postValue(SplashState.DashboardActivity)
        }
    }

    sealed class SplashState{
        object DashboardActivity : SplashState()
    }
}