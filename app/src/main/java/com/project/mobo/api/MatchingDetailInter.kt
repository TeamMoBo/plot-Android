package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.util.*
import kotlin.collections.ArrayList

interface MatchingDetailInter{
    //@GET("/matching/info/:matchingIdx")
    @GET("/matching/info/{matchingIdx}")
    fun matchingDetailResponse(
        @Header("authorization") key: String,
        @Path("matchingIdx") matchingIdx: Int
    ): Call<IdxDetailResponse>
    //Call<MatchingDetailResponse>
}

data class IdxDetailResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Person
)

data class Person(
    @SerializedName("name")
    val name: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("hashTag")
    val hashTag: ArrayList<Int>,
    @SerializedName("school")
    val school: String,
    @SerializedName("location")
    val location: String,
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