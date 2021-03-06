package com.project.mobo.api

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserServiceImpl {
    private const val BASE_URL = "http://13.125.48.35:7935"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserService = retrofit.create(UserService::class.java)
    val MainService: MainInter = retrofit.create(MainInter::class.java)
    val signUpService : SignUpInter = retrofit.create(SignUpInter::class.java)
    val haveTicketService : HaveTicketInter = retrofit.create(HaveTicketInter::class.java)
    val ticketBuyService : TicketBuyInter = retrofit.create(TicketBuyInter::class.java)
    val movieList: MovieList = retrofit.create(MovieList::class.java)
    val choiceTimeService: ChoiceTimeInter = retrofit.create(ChoiceTimeInter::class.java)
    val myPageService: MypageInter = retrofit.create(MypageInter::class.java)
    val historyService: MatchingHistoryInter = retrofit.create(MatchingHistoryInter::class.java)
    val detailService: MatchingDetailInter = retrofit.create(MatchingDetailInter::class.java)
    val matchingDetailService: MatchingInter = retrofit.create(MatchingInter::class.java)
}