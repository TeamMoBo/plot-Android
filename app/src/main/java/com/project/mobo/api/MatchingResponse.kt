package com.project.mobo.api

data class MatchingResponse(
    val status: Int,
    val message: String,
    val data:Matching
)

data class Matching(
    val name: String,
    val img:String,
    val age:Int,
    val school:String,
    val major:String,
    val location:String,
    val genreHash:ArrayList<String>,
    val charmingHash:ArrayList<String>,
    val favorHash:ArrayList<String>,
    val movieInfo:String
)