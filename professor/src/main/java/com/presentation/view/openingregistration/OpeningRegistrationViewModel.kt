package com.labpanel.feature.professor.presentation.view.openingregistration

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labpanel.feature.common.domain.helper.RegexHelper
import com.labpanel.feature.common.domain.model.OpeningsDataModel
import com.labpanel.feature.professor.data.professorrepository.ProfessorRepository
import com.labpanel.feature.professor.domain.states.AddValueEventState
import com.labpanel.feature.professor.domain.states.OpeningDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpeningRegistrationViewModel(val repository: ProfessorRepository): ViewModel() {

    private val openingDataState: MutableLiveData<OpeningDataState> = MutableLiveData()
    val onOpeningDataState: LiveData<OpeningDataState> = openingDataState

    private val addValueEventState: MutableLiveData<AddValueEventState> = MutableLiveData()
    val onAddValueEventState: LiveData<AddValueEventState> = addValueEventState

    fun addDataToFirebase(openingModel: OpeningsDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDataToFirebase(openingModel, addValueEventState)
        }
    }

    fun sendOpeningDataToDatabase(title: String, description: String, activities: String,
                                  prerequisites: String, email: String, degree: String) {

        if(!validInputData(title, description, activities, prerequisites, email) &&
            validateEmail(email)) {
            openingDataState.value = OpeningDataState.ValidOpeningDataState(getOpening(title,
                description, activities, prerequisites, email, degree))
        } else {
            openingDataState.value = OpeningDataState.InvalidOpeningDataState
        }

    }

    fun validateEmail(email: String) = RegexHelper.validateEmail(email)

    private fun validInputData(title: String, description: String, activities: String,
                       prerequisites: String, email: String): Boolean {

        return TextUtils.isEmpty(title) && TextUtils.isEmpty(description) &&
                TextUtils.isEmpty(activities) && TextUtils.isEmpty(prerequisites) &&
                TextUtils.isEmpty(email)
    }

    private fun getOpening(title: String, description: String, activities: String,
                           prerequisites: String, email: String, degree: String): OpeningsDataModel {

        return OpeningsDataModel(title = title, description = description,
            activities = activities, prerequisites = prerequisites, email = email, degree = degree)
    }
}