package com.data.mappers

import com.data.model.OpeningsResponse
import com.openinginfo.domain.model.Openings

class OpeningsMapper {

    fun map(openings: List<OpeningsResponse>): List<Openings> {
        val mappedOpenings = mutableListOf<Openings>()
        openings.map {
            mappedOpenings.add(
                Openings(
                    title = it.title,
                    description = it.description,
                    activities = it.activities,
                    prerequisites = it.prerequisites,
                    email = it.email,
                    degree = it.degree
                )
            )
        }
        return mappedOpenings
    }
}