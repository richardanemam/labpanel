package com.openinginfo.presentation.mappers

import com.openinginfo.domain.model.OpeningVO
import com.openinginfo.domain.model.Openings

object OpeningDataMapper {

    fun map(openings: Openings): OpeningVO {
        return with(openings) {
                OpeningVO(
                    title = title,
                    description = description,
                    activities =activities,
                    prerequisites = prerequisites,
                    email = email,
                    degree = degree
                )
        }
    }
}