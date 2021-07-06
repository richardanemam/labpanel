package com.labpanel.feature.app.domain.listener

import com.labpanel.feature.app.domain.model.OpeningsDataModel

interface DetailsListener {
    fun onClickDetailsButton(openingInfo: OpeningsDataModel)
}