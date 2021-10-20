package com.openinginfo.domain.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OpeningVO(var title: String? = null, var description: String? = null,
                     var activities: String? = null, var prerequisites: String? = null,
                     var email: String? = null, var degree: String? = null
): Parcelable