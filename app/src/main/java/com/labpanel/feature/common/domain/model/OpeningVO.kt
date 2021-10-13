package com.labpanel.feature.common.domain.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties
data class OpeningsDataModel(var title: String? = null, var description: String? = null, var activities: String? = null,
                             var prerequisites: String? = null, var email: String? = null, var degree: String? = null): Parcelable