package com.presentation.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.listener.FirebaseCallback
import com.openinginfo.domain.model.Openings
import com.domain.usecase.ProfileUseCase
import com.presentation.states.LoadingState
import com.presentation.states.ProfessorOpeningsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(val useCase: ProfileUseCase) : ViewModel() {

    private val professorOpeningsState: MutableLiveData<ProfessorOpeningsState> = MutableLiveData()
    val onProfessorOpeningsState: LiveData<ProfessorOpeningsState> = professorOpeningsState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun getOpenings() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingState.postValue(LoadingState.Show)
            useCase.fetchOpeningsFromFirebase(object: FirebaseCallback {
                override fun onCallback(openings: List<Openings>) {
                    openingDataToSubscriber(openings)
                }
            })

        }.invokeOnCompletion {
            loadingState.postValue(LoadingState.Hide)
        }
    }

    private fun openingDataToSubscriber(openings: List<Openings>) {
        if (openings.isNullOrEmpty()) {
            professorOpeningsState.postValue(ProfessorOpeningsState.UnavailableOpenings)
        } else {
            professorOpeningsState.postValue(ProfessorOpeningsState.AvailableOpenings(openings))
        }
    }

    fun getInitials(name: String) = useCase.getInitials(name)
}