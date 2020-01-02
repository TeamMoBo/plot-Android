package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mobo.api.Chatting
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.chat.ChattingActivity
import kotlinx.android.synthetic.main.activity_matching_waiting.*

class MatchingWaitingActivity : AppCompatActivity() {

    private lateinit var addressData: Chatting
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_waiting)

        btnChattingStart.setOnClickListener {
            Log.d("test", "click")
            val callChatting = UserServiceImpl.userService.responseAddress(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjEwNiwiaWF0IjoxNTc3OTYyMjI4LCJleHAiOjE1Nzg1NjcwMjgsImlzcyI6Im1vYm9tYXN0ZXIifQ.0WjcIhqwjRVc-B_DxLbbyRz_OgxR-L-r6W1J1kMj8CI"
                //SharedPreferenceController.getUserToken(this)
            )
            callChatting.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    Log.d("test", "서버 통신")
                    addressData = it.body()!!.data

                    val chatting = Intent(this, ChattingActivity::class.java)
                    chatting.putExtra("address", addressData.address)
                    chatting.putExtra("uid", addressData.uid)
                    Log.v("address", addressData.address)
                    Log.v("test", addressData.uid)
                    startActivity(chatting)
                    //finish()
                }
            }
            )
        }

    }
}
