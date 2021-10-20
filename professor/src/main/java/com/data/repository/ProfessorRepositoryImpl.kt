package com.data.repository

import com.data.api.FirebaseService
import com.data.mappers.OpeningsMapper
import com.data.model.Opening
import com.openinginfo.domain.model.Openings
import com.domain.repository.ProfessorRepository

internal class ProfessorRepositoryImpl(
    private val firebaseService: FirebaseService,
    private val openingsMapper: OpeningsMapper
): ProfessorRepository {

    override suspend fun fetchOpenings(): List<Openings> {
         return openingsMapper.map(firebaseService.fetchOpenings())
    }

    override suspend fun addDataToFirebase(opening: Opening): Int {
        return firebaseService.addDataToFirebase(opening)
    }
}