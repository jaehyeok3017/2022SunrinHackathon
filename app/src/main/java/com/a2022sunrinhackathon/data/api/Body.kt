package com.a2022sunrinhackathon.data.api

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("items") val items: List<Item>,
    val numOfRows: String,
    val pageNo: String,
    val totalCount: String
)