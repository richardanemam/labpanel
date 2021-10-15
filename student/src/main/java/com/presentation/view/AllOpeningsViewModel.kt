package com.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.StudentRepository
import com.openinginfo.presentation.states.OpeningsState
import com.presentation.states.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllOpeningsViewModel(val repository: StudentRepository): ViewModel() {

    private val openingsState: MutableLiveData<OpeningsState> = MutableLiveData()
    val onOpeningsState: LiveData<OpeningsState> = openingsState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun fetchAllOpenings() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingState.postValue(LoadingState.Show)
            repository.fetchAllOpenings(openingsState)
        }.invokeOnCompletion {
            loadingState.postValue(LoadingState.Hide)
        }
    }
}