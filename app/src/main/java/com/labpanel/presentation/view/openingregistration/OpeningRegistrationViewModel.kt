package com.labpanel.presentation.view.openingregistration

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labpanel.domain.auth.helper.EmailValidationHelper
import com.labpanel.domain.model.OpeningModel

class OpeningRegistrationViewModel: ViewModel() {

    private val openingDataState: MutableLiveData<OpeningDataState> = MutableLiveData()
    val onOpeningDataState: LiveData<OpeningDataState> = openingDataState

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

    fun validateEmail(email: String) = EmailValidationHelper.validateEmail(email)

    private fun validInputData(title: String, description: String, activities: String,
                       prerequisites: String, email: String): Boolean {

        return TextUtils.isEmpty(title) && TextUtils.isEmpty(description) &&
                TextUtils.isEmpty(activities) && TextUtils.isEmpty(prerequisites) &&
                TextUtils.isEmpty(email)
    }

    private fun getOpening(title: String, description: String, activities: String,
                           prerequisites: String, email: String, degree: String): OpeningModel {

        return OpeningModel(title = title, description = description,
            activities = activities, prerequisites = prerequisites, email = email, degree = degree)
    }

}