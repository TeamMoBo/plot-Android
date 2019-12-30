package com.project.mobo.temp

data class TempResponse (
    var status : Int,
    var message : String,
    var data : Token
)

data  class Token(
    var token : String
)