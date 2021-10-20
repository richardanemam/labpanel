package com.domain.usecase

import com.data.model.Opening
import com.domain.extensions.isAValidEmail
import com.domain.extensions.isValid
import com.domain.repository.ProfessorRepository

class OpeningRegistrationUseCase(val repository: ProfessorRepository) {
    suspend fun addDataToFirebase(opening: Opening) = repository.addDataToFirebase(opening)

    fun validateOpeningRegistrationData(
        title: String, description: String, activities: String,
        prerequisites: String, email: String
    ): Boolean {
        return validateOpeningInputData(title, description, activities, prerequisites, email)
    }

    fun validateEmail(email: String) = email.isAValidEmail()
    fun validateTextInput(text: String) = text.isValid()

    private fun validateOpeningInputData(
        title: String, description: String, activities: String,
        prerequisites: String, email: String
    ): Boolean {
        return title.isValid() && description.isValid() &&
                activities.isValid() && prerequisites.isValid() &&
                email.isAValidEmail()
    }
}