package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import com.project.mobo.temp.MainResponse
import com.project.mobo.temp.TempResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    /**
     *로그인
     */

    @POST("/user/testlogin")
    fun requestSignIn(
        @Body signInRequest: SignInRequest
    ): Call<TempResponse>

    @POST("/user/signUp")
    fun requestSignUp(
        @Body signUpRequest: SignUpRequest
    ): Call<SignUpResponse>

    @GET("/main")
    fun mainResponse(
        @Header("Authorization") key: String
    ): Call<MainResponse>

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
    val message: String
    //val data: LoginData
)

//data class LoginData(
//    val token : String
//)

//요청
data class SignUpRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("selectGender")
    val selectGender: String,
    @SerializedName("selectMinAge")
    val selectMinAge: Int,
    @SerializedName("selectMaxAge")
    val selectMaxAge: Int,
    @SerializedName("preferGenre")
    val preferGenre: String,
    @SerializedName("attractPoint")
    val attractPoint: String,
    @SerializedName("favor")
    val favor: String,
    @SerializedName("school")
    val school: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("kakao")
    val kakao: String
)


//응답
data class SignUpResponse(
    val status: Int,
    val message: String
)



