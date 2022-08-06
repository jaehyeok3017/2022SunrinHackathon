package com.a2022sunrinhackathon.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val baseUrl = "http://api.data.go.kr/openapi/tn_pubr_public_free_mlsv_api"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}