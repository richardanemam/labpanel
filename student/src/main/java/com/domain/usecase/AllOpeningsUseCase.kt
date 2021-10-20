package com.domain.usecase

import com.core.listener.FirebaseCallback
import com.domain.repository.StudentRepository

class AllOpeningsUseCase(private val repository: StudentRepository) {

    suspend fun retrieveAllOpenings(firebaseCallback: FirebaseCallback) = repository.fetchAllOpenings(firebaseCallback)
}