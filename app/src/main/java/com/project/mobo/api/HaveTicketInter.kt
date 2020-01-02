package com.project.mobo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HaveTicketInter{
    @GET("/mypage/ticket")
    fun responseHaveTicket(
        @Header("Authorization") key: String
    ) : Call<HaveTicketResponse>
}

data class HaveTicketResponse(
    val status : Int,
    val message : String,
    val data : ArrayList<HaveTicketData>
)

data class HaveTicketData(
    val userTicket : Int,
    val userPopcorn : Int
)
