package com.domain.repository

import com.core.listener.FirebaseCallback
import com.data.model.Opening

interface ProfessorRepository {
    suspend fun fetchOpenings(firebaseCallback: FirebaseCallback)
    suspend fun addDataToFirebase(opening: Opening): Int
}