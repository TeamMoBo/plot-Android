package com.project.mobo

import android.content.Context
import android.content.SharedPreferences
import khronos.Dates
import khronos.days
import khronos.plus
import java.util.*

object SharedPreferenceController {

    private val USER_ID = "userid"
    private val TIME_DATE = "timedate"
    private val TOKEN = "token"
    private val USERNAME = "username"
    //private val TIME_DATE_DATA : Pair<Date, MutableSet<String>>


    fun setUserId(context: Context, id: String) {
        val pref = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(USER_ID, id)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val pref = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        return pref.getString(USER_ID, "") ?: ""
    }

    //시간선택 데이터 저장
    fun setTimeTable(context: Context, date: Date, timetable: MutableSet<String>) {
        val pref = context.getSharedPreferences(TIME_DATE, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putStringSet(date.toString(), timetable)
        editor.commit()
    }

    //시간선택 데이터 가져오기
    fun getTimeTable(context: Context, date: Date): Pair<Date, MutableSet<String>?> {
        val pref = context.getSharedPreferences(TIME_DATE, Context.MODE_PRIVATE)
        var set: MutableSet<String>? = pref.getStringSet(date.toString(), HashSet<String>());
        return Pair(date, set)
    }

    fun setUserToken(context: Context, token: String) {
        val pref = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getUserToken(context: Context): String {
        val pref = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        return pref.getString(TOKEN, "") ?: ""
    }

    fun setUserName(context: Context, UserName: String) {
        val pref = context.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(USERNAME, UserName)
        editor.apply()
    }

    fun getUserName(context: Context): String {
        val pref = context.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)
        return pref.getString(USERNAME, "") ?: ""
    }


//    fun clearUserSharedPreferences(ctx: Context){
//        val preference : SharedPreferences =
//        ctx.getSharedPreferences(USER_)
//    }
}