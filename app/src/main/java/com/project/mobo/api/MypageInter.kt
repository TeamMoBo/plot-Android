package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageInter{
    @GET("/mypage/info")
    fun myPageRead(
        @Header("authorization") token: String
    ): Call<MypageData>
}

data class MypageData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: MyPageDataD
)

data class MyPageDataD(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userNickname")
    val userNickname:String,
    @SerializedName("userAge")
    val userAge: Int,
    @SerializedName("userComment")
    val userComment:String,
    @SerializedName("userImg")
    val userImg: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userLocation")
    val userLocation: String,
    @SerializedName("userSelectGender")
    val userSelectGender: Int,
    @SerializedName("userSelectMinAge")
    val userSelectMinAge: Int,
    @SerializedName("userSelectMaxAge")
    val userSelectMaxAge: Int,
    @SerializedName("userSchool")
    val userSchool: String,
    @SerializedName("userMajor")
    val userMajor: String,
    @SerializedName("userKakao")
    val userKakao: String
)