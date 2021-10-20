package com.domain.usecase

import com.domain.repository.StudentRepository

class AllOpeningsUseCase(private val repository: StudentRepository) {
    suspend fun retrieveAllOpenings() = repository.fetchAllOpenings()
}