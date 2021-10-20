package com.domain.repository

import com.data.model.Opening
import com.openinginfo.domain.model.Openings

interface ProfessorRepository {
    suspend fun fetchOpenings(): List<Openings>
    suspend fun addDataToFirebase(opening: Opening): Int
}