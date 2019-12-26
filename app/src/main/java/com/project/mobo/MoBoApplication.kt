package com.project.mobo

import android.app.Application
import android.util.Log
import com.sendbird.android.SendBird

class MoBoApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        initSendbird()
        //SendBird.init("EBA68579-A830-46E4-81B4-B822616C7FF3", this)
    }

    private fun initSendbird(){
        if (SendBird.init("EBA68579-A830-46E4-81B4-B822616C7FF3", this)) {
            Log.d(LOG_HEAD, "SendBird init 성공")
        }
        else {
            Log.e(LOG_HEAD, "SendBird init 실패")
        }
    }

    companion object {
        const val LOG_HEAD = "jihye-debug"
    }
    //TODO: connect는 로그인 할때 구현
}