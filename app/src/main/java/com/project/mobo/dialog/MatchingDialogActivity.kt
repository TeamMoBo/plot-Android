package com.project.mobo.dialog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.api.*
import com.project.mobo.util.registerEvent
import kotlinx.android.synthetic.main.popup_matching_choice.*
import java.util.*

class MatchingDialogActivity : AppCompatActivity() {

    //private lateinit var createdTime2: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_matching_choice)



        //타이머 기능
        //timer()

        btnChoiceYes.setOnClickListener {
            val callMatchingDecision = UserServiceImpl.userService.requestMatchingDecision(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjEwNiwiaWF0IjoxNTc3OTYyMjI4LCJleHAiOjE1Nzg1NjcwMjgsImlzcyI6Im1vYm9tYXN0ZXIifQ.0WjcIhqwjRVc-B_DxLbbyRz_OgxR-L-r6W1J1kMj8CI",
                matchingDecisionRequest = MatchingDecisionRequest(true)
            )
            callMatchingDecision.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    if (it.body()!!.status == 200) {
                        val choice = Intent(this, SuccessDialogActivity::class.java)
                        startActivity(choice)
                        finish()
                    }
                }
            }
            )
        }
        btnChoiceNo.setOnClickListener {
            val callMatchingDecision = UserServiceImpl.userService.requestMatchingDecision(
                //SharedPreferenceController.getUserToken(this), MatchingDecisionRequest(false)
                //objects.myToken, MatchingDecisionRequest(false)
                key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjQwLCJpYXQiOjE1Nzc5NDkzNDYsImV4cCI6MTU3ODU1NDE0NiwiaXNzIjoibW9ib21hc3RlciJ9.dwKFFXHdDhkb8WW25BSMyig5DFzUlKPQ-WE1lzO4JBc",
                matchingDecisionRequest = MatchingDecisionRequest(false)
            )
            callMatchingDecision.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    if (it.body()!!.status == 202) {
                        val choice = Intent(this, FailDialogActivity::class.java)
                        startActivity(choice)
                        finish()
                    }
                }
            })

        }
    }

    private fun timer() {
        //액티비티가 만들어진 시간
        //createdTime2 = Date()

//        registerEvent(createdTime2, 0.5f) {
//            finish()
//        }
    }
}