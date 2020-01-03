package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MatchingInter{
    @GET("/matching/info/{matchingIdx}")
    fun matchingDetailResponse(
        @Header("authorization") token: String,
        @Path("matchingIdx") matchingIdx: String
    ): Call<MatchingData>
}

data class MatchingData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: MatchingUserData
)

data class MatchingUserData(
    @SerializedName("name")
    val name: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("hashtTag")
    val hashtTag: ArrayList<String>,
    @SerializedName("school")
    val school: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("kakaotalk")
    val kakaotalk: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("movieTitle")
    val movieTitle:String,
    @SerializedName("date")
    val date: String
)
