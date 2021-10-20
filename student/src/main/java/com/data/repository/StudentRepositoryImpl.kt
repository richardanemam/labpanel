package com.data.repository

import com.data.api.StudentFirebaseService
import com.data.mappers.OpeningsMapper
import com.data.model.OpeningsResponse
import com.domain.FirabaseCallback
import com.domain.repository.StudentRepository

internal class StudentRepositoryImpl(
    private val studentFirebaseService: StudentFirebaseService,
    private val openingsMapper: OpeningsMapper
) : StudentRepository {

    override suspend fun fetchAllOpenings(firebaseCallback: FirabaseCallback){
        studentFirebaseService.fetchAllOpenings(firebaseCallback, openingsMapper)
    }

    override fun mapOpenings(openings: List<OpeningsResponse>) {
        openingsMapper.map(openings)
    }
}