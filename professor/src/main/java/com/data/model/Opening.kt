package com.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Opening(var title: String? = null, var description: String? = null,
                            var activities: String? = null, var prerequisites: String? = null,
                            var email: String? = null, var degree: String? = null
)
