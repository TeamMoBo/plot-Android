package com.project.mobo.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface TicketBuyInter {
    @PUT
        ("/mypage/ticket")
    fun putTicketBuy(
        @Header("Authorization") key: String,
        @Body ticketBuyData : TicketBuyData
    ): Call<TicketBuyResponse>
}
data class TicketBuyResponse(
    val status : Int,
    val message : String,
    val data : TicketBuyData
)

data class TicketBuyData(
    var userTicket : Int,
    var userPopcorn : Int
)

