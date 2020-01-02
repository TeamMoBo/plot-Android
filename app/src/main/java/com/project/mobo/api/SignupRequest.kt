package com.project.mobo.api

//요청
data class SignupRequest(
    val id : String,
    val password:String,
    val name:String,
    val nickname:String,
    val age:Int,
    val comment:String,
    val location: String,
    val gender:Int,
    val selectGender:Int,
    val selectMinAge:Int,
    val selectMaxAge:Int,
    val preferGenre:String,
    val attractPoint:String,
    var favor:String,
    val school:String,
    val major:String,
    val kakao:String
)