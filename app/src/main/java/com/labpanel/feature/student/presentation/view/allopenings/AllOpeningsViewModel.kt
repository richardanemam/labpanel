package com.labpanel.feature.student.presentation.view.allopenings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labpanel.feature.app.presentation.view.viewevents.LoadingState
import com.labpanel.feature.professor.domain.states.OpeningsState
import com.labpanel.feature.student.data.StudentRepository
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