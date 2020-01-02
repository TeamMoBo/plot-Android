package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignUpInter{
    @POST("/user/signup")
    fun requestSignUp(
        @Body requestSignUp: SignupRequest
    ): Call<SignupResponse>
}

data class SignupRequest(
    @SerializedName("id")
    val id : String,
    @SerializedName("password")
    val password:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("age")
    val age:Int,
    @SerializedName("comment")
    val comment:String,
    @SerializedName("location")
    val location: String,
    @SerializedName("gender")
    val gender:Int,
    @SerializedName("selectGender")
    val selectGender:Int,
    @SerializedName("selectMinAge")
    val selectMinAge:Int,
    @SerializedName("selectMaxAge")
    val selectMaxAge:Int,
    @SerializedName("preferGenre")
    val preferGenre:String,
    @SerializedName("attractPoint")
    val attractPoint:String,
    @SerializedName("favor")
    val favor:String,
    @SerializedName("school")
    val school:String,
    @SerializedName("major")
    val major:String,
    @SerializedName("kakao")
    val kakao:String
)

//응답
data class SignupResponse(
    val status: Int,
    val message: String
)