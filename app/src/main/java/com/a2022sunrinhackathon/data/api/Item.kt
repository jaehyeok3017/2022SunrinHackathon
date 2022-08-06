package com.a2022sunrinhackathon.data.api

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("fcltyNm") val fcltyNm: String,
    @SerializedName("insttCode") val insttCode: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("lnmadr") val lnmadr: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("mlsvDate") val mlsvDate: String,
    @SerializedName("mlsvPlace") val mlsvPlace: String,
    @SerializedName("mlsvTime") val mlsvTime: String,
    @SerializedName("mlsvTrget") val mlsvTrget: String,
    @SerializedName("openCloseDate") val operCloseDate: String,
    @SerializedName("operInstitutionNm") val operInstitutionNm: String,
    @SerializedName("operOpenDate") val operOpenDate: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("rdnmadr") val rdnmadr: String,
    @SerializedName("referenceDate") val referenceDate: String
){

}