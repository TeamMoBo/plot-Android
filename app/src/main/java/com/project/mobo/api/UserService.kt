package com.project.mobo.api

import retrofit2.Call
import retrofit2.http.*

interface UserService {
    /**
     *로그인
     */

//    @POST("/user/testlogin")
//    fun requestSignIn(
//        @Header("Content-Type") content_type: String
//    ): Call<SignInResponse>

    /**
     *회원가입
     */
    @POST("/user/signup")
    fun requestSignUp(
        @Header("Content-Type") content_type: SignupRequest
    ): Call<SignupResponse>

//    @GET("/main")
//    fun mainResponse(
//        @Header("Authorization") key: String
//    ): Call<MainResponse>

}

////요청-데이터 선언
//data class SignInRequest(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("password")
//    val password: String
//)
//
////응답-데이터 선언
//data class SignInResponse(
//    val status: Int,
//    val message: String,
//    val data: LoginData
//)
//
//data class LoginData(
//    val token : String
//)



