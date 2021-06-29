package com.labpanel.presentation.view.profile

import androidx.lifecycle.ViewModel
import com.labpanel.data.professorrepository.ProfessorRepository
import java.lang.StringBuilder
import java.util.*

class ProfileViewModel(repository: ProfessorRepository): ViewModel() {



    companion object {
        private const val FIRST_POSITION = 0
    }

    fun getOpenings() {

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