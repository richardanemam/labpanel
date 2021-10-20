package com.domain.usecase

import com.domain.FirabaseCallback
import com.domain.repository.StudentRepository

class AllOpeningsUseCase(private val repository: StudentRepository) {

    suspend fun retrieveAllOpenings(firabaseCallback: FirabaseCallback) = repository.fetchAllOpenings(firabaseCallback)
}