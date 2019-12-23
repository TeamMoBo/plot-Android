package com.project.mobo

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private val USER_ID = "userid"

    fun setUserId(context: Context, id : String){
        val pref = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(USER_ID, id)
        editor.commit()
    }

    fun getUserId(context: Context) : String{
        val pref = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        return pref.getString(USER_ID, "")?:""
    }

//    fun clearUserSharedPreferences(ctx: Context){
//        val preference : SharedPreferences = ctx.getSharedPreferences(USER_)
//    }
}