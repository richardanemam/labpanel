package com.openinginfo.domain.listener

import com.openinginfo.domain.model.OpeningsDataModel


interface DetailsListener {
    fun onClickDetailsButton(openingInfo: OpeningsDataModel)
}