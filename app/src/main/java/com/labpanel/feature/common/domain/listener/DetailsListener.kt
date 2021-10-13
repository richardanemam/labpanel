package com.labpanel.feature.common.domain.listener

import com.labpanel.feature.common.domain.model.OpeningsDataModel

interface DetailsListener {
    fun onClickDetailsButton(openingInfo: OpeningsDataModel)
}