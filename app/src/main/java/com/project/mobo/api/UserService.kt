package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.lang.NullPointerException

interface UserService {
    @POST("/user/testlogin")
    fun requestSignIn(
        @Header("Content-Type") content_type: String,
        @Body signInRequest: SignInRequest
    ): Call<SignInResponse>

    @POST("/user/testsignin")
    fun requestSignUp(
        @Header("Content-Type") content_type: String,
        @Body signUpRequest: SignUpRequest
    ):Call<SignUpResponse>

    //
}

//요청-데이터 선언
data class SignInRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String
)

//응답-데이터 선언
data class SignInResponse(
    val status: Int,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val token : String
)

data class SignUpRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String
)

//요청
/*
data class SignUpRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("selectGender")
    val selectGender: String,
    @SerializedName("selectMinAge")
    val selectMinAge: String,
    @SerializedName("selectMaxAge")
    val selectMaxAge: String,
    @SerializedName("preferGenre")
    val preferGenre: String,
    @SerializedName("attractPoint")
    val attractPoint: String,
    @SerializedName("myLikes")
    val myLikes: String,
    @SerializedName("school")
    val school: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("kakao")
    val kakao: String
)*/

//응답
data class SignUpResponse(
    val status: Int,
    val message: String
)



