package com.domain.repository

import com.data.model.OpeningsResponse
import com.domain.FirabaseCallback

interface StudentRepository {
    suspend fun fetchAllOpenings(firebaseCallback: FirabaseCallback)
    fun mapOpenings(openings: List<OpeningsResponse>)
}