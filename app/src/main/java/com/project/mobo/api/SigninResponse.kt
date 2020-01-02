package com.project.mobo.api

data class SigninResponse(
    val status: Int,
    val message: String,
    val data: Token
)

data  class Token(
    val token : String
)
