package com.project.mobo.api

import com.google.gson.annotations.SerializedName
import com.project.mobo.temp.MainResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    /**
     *로그인 V
     */
    @POST("/user/signin")
    fun requestSignIn(
        @Body signinRequest: SigninRequest
    ): Call<SigninResponse>

    /**
     *회원가입
     */
    @POST("/user/signup")
    fun requestSignUp(
        @Body signupRequest: SignupRequest
    ): Call<SignupResponse>

//    @GET("/main")
//    fun mainResponse(
//        @Header("Authorization") key: String
//    ): Call<MainResponse>

    /**
     *매칭 화면
     */
    @GET("/matching")
    fun responseMatching(
        @Header("authorization") key: String
        ):Call<MatchingResponse>

    /**
     *매칭 확인/취소 요청
     */
    @POST("/matching/comfirm")
    fun requestMatchingConfirm(
        @Header("authorization") key: String,
        @Body matchingConfirmRequest: MatchingConfirmRequest
    ):Call<MatchingConfirmResponse>

    /**
     *채팅 주소 요청 V
     */
    @GET("/matching/address")
    fun responseAddress(
        @Header("authorization") key: String
    ): Call<AddressResponse>

    /**
     *매칭 결정
     */
    @POST("/matching/decision")
    fun requestMatchingDecision(
        @Header("authorization") key: String,
        @Body matchingDecisionRequest: MatchingDecisionRequest
    ): Call<MatchingDecisionResponse>



}

////요청-데이터 선언
//data class SignInRequest(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("password")
//    val password: String
//)

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



