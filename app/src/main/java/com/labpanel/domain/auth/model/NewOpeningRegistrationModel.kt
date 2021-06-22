package com.labpanel.domain.auth.model


data class NewOpeningRegistrationModel(val title: String, val description: String, val activities: String,
                                       val prerequisites: String, val email: String, val degree: String)
