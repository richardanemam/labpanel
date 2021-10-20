package com.core.listener

import com.openinginfo.domain.model.Openings

interface FirebaseCallback {
    fun onCallback(openings: List<Openings>)
}