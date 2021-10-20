package com.domain.repository

import com.openinginfo.domain.model.Openings

interface StudentRepository {
    suspend fun fetchAllOpenings(): List<Openings>
}