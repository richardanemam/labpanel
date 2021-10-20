package com.data.repository

import com.data.api.ProfessorFirebaseService
import com.data.mappers.OpeningsMapper
import com.data.model.Opening
import com.openinginfo.domain.model.Openings
import com.domain.repository.ProfessorRepository

internal class ProfessorRepositoryImpl(
    private val professorFirebaseService: ProfessorFirebaseService,
    private val openingsMapper: OpeningsMapper
): ProfessorRepository {

    override suspend fun fetchOpenings(): List<Openings> {
         return openingsMapper.map(professorFirebaseService.fetchOpenings())
    }

    override suspend fun addDataToFirebase(opening: Opening): Int {
        return professorFirebaseService.addDataToFirebase(opening)
    }
}