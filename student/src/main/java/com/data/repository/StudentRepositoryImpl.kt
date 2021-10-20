package com.data.repository

import com.data.api.StudentFirebaseService
import com.data.mappers.OpeningsMapper
import com.domain.repository.StudentRepository
import com.openinginfo.domain.model.Openings

internal class StudentRepositoryImpl(private val studentFirebaseService: StudentFirebaseService,
                            private val openingsMapper: OpeningsMapper
): StudentRepository {

    override suspend fun fetchAllOpenings(): List<Openings> {
        return openingsMapper.map(studentFirebaseService.fetchAllOpenings())
    }
}