package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MainInter{
    @GET("/main")
    fun mainResponse(
        @Header("authorization") key: String
    ): Call<MainResponse>
}

data class MainResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("userIdx")
    val userIdx: Int,
    @SerializedName("matchingState")
    val matchingState: Int,
    @SerializedName("randMovie")
    val randMovie: ArrayList<RandMovie>,
    @SerializedName("reserveMovie")
    val reserveMovie: ArrayList<ReserveMovie>,
    @SerializedName("reserveDate")
    val reserveDate: ArrayList<ReserveDate>
)

data class RandMovie(
    @SerializedName("movieIdx")
    val movieIdx: Int,
    @SerializedName("movieImg")
    val movieImg: String?,
    @SerializedName("movieName")
    val movieName: String,
    @SerializedName("movieScore")
    val movieScore: Float,
    @SerializedName("movieRuntime")
    val movieRuntime: String,
    @SerializedName("movieGenre")
    val movieGenre: String
)

data class ReserveMovie(
    @SerializedName("movieIdx")
    val movieIdx: Int,
    @SerializedName("movieImg")
    val movieImg: String?,
    @SerializedName("movieName")
    val movieName: String,
    @SerializedName("movieScore")
    val movieScore: Float
)
data class ReserveDate(
    @SerializedName("reservationDate")
    val reservationDate: String,
    @SerializedName("reservationWeekday")
    val reservationWeekday: String,
    @SerializedName("reservationTime")
    val reservationTime: ArrayList<Int>
)