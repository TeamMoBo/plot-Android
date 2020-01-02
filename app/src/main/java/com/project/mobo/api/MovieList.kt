package com.project.mobo.api

import android.telecom.Call
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieList{

    //영화 목록 조회
    @GET("/movie/{movieReleaseStatus}")
    fun movieTopResopnse(
        @Path("movieReleaseStatus") movieReleaseStatus: String
    ): retrofit2.Call<MovieTopResponse>
}

data class MovieTopResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Movies
)

data class Movies(
    @SerializedName("movieReleaseStatus")
    val movieReleaseStatus: Int,
    @SerializedName("movieData")
    val movieData: List<MoviesDetail>
)

data class MoviesDetail(
    @SerializedName("movieIdx")
    val movieIdx: Int,
    @SerializedName("movieName")
    val movieName: String,
    @SerializedName("movieScore")
    val movieScore: Float,
    @SerializedName("movieImg")
    val movieImg: String
)