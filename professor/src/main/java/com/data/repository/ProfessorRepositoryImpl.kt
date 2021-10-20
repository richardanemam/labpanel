package com.data.repository

import com.core.listener.FirebaseCallback
import com.data.api.ProfessorFirebaseService
import com.data.mappers.OpeningsMapper
import com.data.model.Opening
import com.domain.repository.ProfessorRepository

internal class ProfessorRepositoryImpl(
    private val professorFirebaseService: ProfessorFirebaseService,
    private val openingsMapper: OpeningsMapper
): ProfessorRepository {

    override suspend fun fetchOpenings(firebaseCallback: FirebaseCallback) {
         professorFirebaseService.fetchOpenings(firebaseCallback, openingsMapper)
    }

    override suspend fun addDataToFirebase(opening: Opening): Int {
        return professorFirebaseService.addDataToFirebase(opening)
    }
}