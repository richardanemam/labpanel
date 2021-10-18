package com.presentation.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.ProfessorRepository
import com.openinginfo.presentation.states.OpeningsState
import com.presentation.states.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.util.*

class ProfileViewModel(val repository: ProfessorRepository) : ViewModel() {

    companion object {
        private const val FIRST_POSITION = 0
    }

    private val openingsState: MutableLiveData<OpeningsState> = MutableLiveData()
    val onOpeningsState: LiveData<OpeningsState> = openingsState

    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    val onLoadingState: LiveData<LoadingState> = loadingState

    fun getOpenings() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingState.postValue(LoadingState.Show)
            repository.fetchOpenings(openingsState)
        }.invokeOnCompletion {
            loadingState.postValue(LoadingState.Hide)
        }
    }

    fun getInitials(name: String): String {
        val builder = StringBuilder()
        val nameParts = name.split(" ").toTypedArray()

        if (nameParts.size > 1) {
            return builder
                .append(nameParts[FIRST_POSITION][FIRST_POSITION].toString())
                .append(nameParts[nameParts.size - 1][FIRST_POSITION].toString())
                .toString()
                .toUpperCase(Locale.ROOT)
        }

        return builder
            .append(nameParts[FIRST_POSITION][FIRST_POSITION].toString())
            .toString()
            .toUpperCase(Locale.ROOT)
    }
}