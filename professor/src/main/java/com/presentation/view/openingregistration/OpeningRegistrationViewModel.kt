package com.presentation.view.openingregistration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.FirebaseResponse.ON_CANCELED
import com.core.FirebaseResponse.ON_DATA_CHANGED
import com.data.model.Opening
import com.domain.usecase.OpeningRegistrationUseCase
import com.presentation.states.AddOpeningValueState
import com.presentation.states.OpeningDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpeningRegistrationViewModel(val useCase: OpeningRegistrationUseCase): ViewModel() {

    private val openingDataState: MutableLiveData<OpeningDataState> = MutableLiveData()
    val onOpeningDataState: LiveData<OpeningDataState> = openingDataState

    private val addOpeningOpeningValueState: MutableLiveData<AddOpeningValueState> = MutableLiveData()
    val onAddOpeningOpeningValueState: LiveData<AddOpeningValueState> = addOpeningOpeningValueState

    fun addDataToFirebase(opening: Opening) {
        viewModelScope.launch(Dispatchers.IO) {
            val statusCode = useCase.addDataToFirebase(opening)
            handleStatusCode(statusCode)
        }
    }

    private fun handleStatusCode(statusCode: Int) {
        when(statusCode) {
            ON_DATA_CHANGED -> addOpeningOpeningValueState.postValue(AddOpeningValueState.DataChanged)
            ON_CANCELED -> addOpeningOpeningValueState.postValue(AddOpeningValueState.DataChanged)
        }
    }

    fun validateOpeningRegistrationData(title: String, description: String, activities: String,
                                        prerequisites: String, email: String, degree: String) {

        if(useCase.validateOpeningRegistrationData(title, description, activities, prerequisites, email)) {
            openingDataState.value = OpeningDataState.ValidOpeningDataState(
                Opening(title, description, activities, prerequisites, email, degree)
            )
        } else {
            openingDataState.value = OpeningDataState.InvalidOpeningDataState
        }
    }

    fun validateEmail(email: String) = useCase.validateEmail(email)
    fun validateTextInput(text: String) = useCase.validateTextInput(text)
}