package com.example.monothon.api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NaverAPI {

    @Multipart
    @POST("/v1/vision/face")
    fun naverCheckFace(
        @Header("X-Naver-Client-Id") id: String?,
        @Header("X-Naver-Client-Secret") secret: String?,
        @Part file: MultipartBody.Part?
    ): Call<NaverAPIRes>
}