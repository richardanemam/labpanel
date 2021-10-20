package com.domain.repository

import com.data.model.OpeningsResponse
import com.core.listener.FirebaseCallback

interface StudentRepository {
    suspend fun fetchAllOpenings(firebaseCallback: FirebaseCallback)
    fun mapOpenings(openings: List<OpeningsResponse>)
}