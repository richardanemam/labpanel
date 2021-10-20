package com.domain.usecase

import com.data.model.Opening
import com.domain.repository.ProfessorRepository
import java.lang.StringBuilder
import java.util.*

class ProfileUseCase(val repository: ProfessorRepository) {

    companion object {
        private const val FIRST_POSITION = 0
    }

    suspend fun fetchOpeningsFromFirebase() = repository.fetchOpenings()
    suspend fun addDataToFirebase(opening: Opening) = repository.addDataToFirebase(opening)

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