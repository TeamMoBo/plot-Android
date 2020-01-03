package com.project.mobo.api

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MatchingHistoryInter{
    @GET("/matching/info")
    fun historyResponse(
        @Header("authorization") key: String
    ): Call<HistoryResponse>
}

data class HistoryResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: ArrayList<HistoryData>
)

data class HistoryData(
    @SerializedName("matchingIdx")
    val matchingIdx: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("kakaotalk")
    val kakaotalk: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("movieTitle")
    val movieTitle: String,
    @SerializedName("state")
    val state: Boolean,
    @SerializedName("date")
    val date: String
)