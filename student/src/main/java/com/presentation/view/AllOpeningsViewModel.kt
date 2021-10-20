package com.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.StudentRepositoryImpl
import com.domain.usecase.AllOpeningsUseCase
import com.openinginfo.domain.model.Openings
import com.openinginfo.presentation.states.OpeningsState
import com.presentation.states.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllOpeningsViewModel(private val useCase: AllOpeningsUseCase): ViewModel() {

    private val openingsState: MutableLiveData<OpeningsState> = MutableLiveData()
    val onOpeningsState: LiveData<OpeningsState> = openingsState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun fetchAllOpenings() {
        viewModelScope.launch {
            loadingState.postValue(LoadingState.Show)
            val allOpenings = useCase.retrieveAllOpenings()
            openingDataToSubscriber(allOpenings)
        }.invokeOnCompletion {
            loadingState.postValue(LoadingState.Hide)
        }
    }

    private fun openingDataToSubscriber(openings: List<Openings>) {
        if (openings.isNullOrEmpty()) {
            openingsState.postValue(OpeningsState.UnavailableOpenings)
        } else {
            openingsState.postValue(OpeningsState.AvailableOpenings(openings))
        }
    }
}