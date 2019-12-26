package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sendbird.android.GroupChannel
import com.sendbird.android.GroupChannelParams
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val userId = edtTestId.text.toString()
            if (userId == "") edtTestId.requestFocus()
            else postLoginResponse(userId)
        }
    }

    fun postLoginResponse(userId: String) {
        GroupChannel.createChannel(GroupChannelParams().apply {
            setPublic(false)
            setEphemeral(false) // 일회성 채팅(true) vs 지속성 채팅(false)
            setDistinct(false) // 같은 유저 상대로 다른 채팅 열기(true) vs 같은 유저 상대로 같은 채팅(false)
            addUserIds(
                listOf(
                    SendbirdConstant.TEST_ID,
                    SendbirdConstant.TEST_ID_OTHER
                )
            ) // TEST_ID 와 TEST_ID_OTHER 의 채팅방
        }) { channel, e ->
            if (e != null) {
                Log.e(MoBoApplication.LOG_HEAD, "create channel 에러 $e")
            }
            Log.d(MoBoApplication.LOG_HEAD, "create channel $channel")
            getUrl()
        }
    }
    private fun getUrl(){


    }
}



