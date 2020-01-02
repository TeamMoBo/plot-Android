package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChoiceTimeInter{
    @POST("/movie")
    fun choiceTimeInter(
        @Header("authorization") token: String,
        @Body choiceRequest: ChoiceRequest
    ): Call<ChoiceResponse>
}

data class ChoiceRequest(
    @SerializedName("movieIdx")
    val movieIdx: List<Int>,
    @SerializedName("reservationDate")
    val reservationDate: String,
    @SerializedName("reservationTime")
    val reservationTime: List<Int>
)

data class ChoiceResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)