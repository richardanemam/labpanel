package com.openinginfo.domain.listener

import com.openinginfo.domain.model.Openings

interface DetailsListener {
    fun onClickDetailsButton(openingInfo: Openings)
}