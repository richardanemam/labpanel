package com.labpanel.feature.common.domain.listener

import com.example.home.domain.model.OpeningsDataModel

interface DetailsListener {
    fun onClickDetailsButton(openingInfo: OpeningsDataModel)
}