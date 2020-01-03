package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.api.Chatting
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.chat.ChattingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var addressData: Chatting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //timer.setOnClickListener

        btnLogin.setOnClickListener {
            val callUrl = UserServiceImpl.userService.responseAddress(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjEwNiwiaWF0IjoxNTc3OTYyMjI4LCJleHAiOjE1Nzg1NjcwMjgsImlzcyI6Im1vYm9tYXN0ZXIifQ.0WjcIhqwjRVc-B_DxLbbyRz_OgxR-L-r6W1J1kMj8CI"
                //SharedPreferenceController.getUserToken(this)
            )
            callUrl.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    addressData = it.body()!!.data

                    val chattings = Intent(this, ChattingActivity::class.java)
                    chattings.putExtra("address", addressData.address)
                    chattings.putExtra("uid", addressData.uid)
                    chattings.putExtra("opponentName", addressData.opponentName)
                    chattings.putExtra("opponentImg", addressData.opponentImg)
                    startActivity(chattings)
                    finish()
                }
            })

            /*
            val UserId_1 = edtUserId_1.text.toString()
            val UserId_2 = edtUserId_2.text.toString()
            if (UserId_1 == "" || UserId_2 == "") {
                edtUserId_1.requestFocus()
                edtUserId_2.requestFocus()
                Toast.makeText(this, "로그인 아이디를 대령하거라.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val intent: Intent = Intent(this, ChattingActivity::class.java)
                intent.putExtra("UserId_1", edtUserId_1.text.toString())
                intent.putExtra("UserId_2", edtUserId_2.text.toString())
                startActivity(intent)
                finish()
            }
             */
        }
    }
}
