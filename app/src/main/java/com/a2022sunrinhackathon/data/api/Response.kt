package com.a2022sunrinhackathon.data.api

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("body") val body: Body,
    val header: Header
)