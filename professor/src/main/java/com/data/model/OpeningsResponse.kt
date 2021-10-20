package com.data.model

import com.google.gson.annotations.SerializedName

data class OpeningsResponse(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("activities")
    var activities: String? = null,
    @SerializedName("prerequisites")
    var prerequisites: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("degree")
    var degree: String? = null
)