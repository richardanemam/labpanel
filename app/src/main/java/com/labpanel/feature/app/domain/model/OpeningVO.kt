package com.labpanel.feature.app.domain.model

import com.google.firebase.database.IgnoreExtraProperties

data class Data(var dataModel: DataModel? = null)

@IgnoreExtraProperties
data class DataModel(var openings: List<OpeningsModel>? = null)

@IgnoreExtraProperties
data class OpeningsModel(var opening: List<OpeningsDataModel>? = null)

@IgnoreExtraProperties
data class OpeningsDataModel(var title: String? = null, var description: String? = null, var activities: String? = null,
                             var prerequisites: String? = null, var email: String? = null, var degree: String? = null)
