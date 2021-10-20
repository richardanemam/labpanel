package com.domain

import com.openinginfo.domain.model.Openings

interface FirabaseCallback {
    fun onCallback(openings: List<Openings>)
}