package com.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.listener.FirebaseCallback
import com.domain.usecase.AllOpeningsUseCase
import com.openinginfo.domain.model.Openings
import com.openinginfo.presentation.states.OpeningsState
import com.presentation.states.LoadingState
import kotlinx.coroutines.launch

class AllOpeningsViewModel(private val useCase: AllOpeningsUseCase): ViewModel() {

    private val openingsState: MutableLiveData<OpeningsState> = MutableLiveData()
    val onOpeningsState: LiveData<OpeningsState> = openingsState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun fetchAllOpenings() {
        viewModelScope.launch {
            loadingState.postValue(LoadingState.Show)
            useCase.retrieveAllOpenings(object: FirebaseCallback {
                override fun onCallback(openings: List<Openings>) {
                    openingDataToSubscriber(openings)
                }
            })
        }.invokeOnCompletion {
            loadingState.postValue(LoadingState.Hide)
        }
    }

    private fun openingDataToSubscriber(openings: List<Openings>?) {
        if (openings.isNullOrEmpty()) {
            openingsState.postValue(OpeningsState.UnavailableOpenings)
        } else {
            openingsState.postValue(OpeningsState.AvailableOpenings(openings))
        }
    }
}