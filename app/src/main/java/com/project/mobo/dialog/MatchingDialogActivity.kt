package com.project.mobo.dialog

import android.annotation.SuppressLint
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
                SharedPreferenceController.getUserToken(this),
                matchingDecisionRequest = MatchingDecisionRequest(true)
            )
            callMatchingDecision.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    if (it.body()!!.status == 200) {
                        val choice = Intent(this, SuccessDialogActivity::class.java)
                        startActivityForResult(choice, 3000)
                    }
                }
            }
            )
        }
        btnChoiceNo.setOnClickListener {
            val callMatchingDecision = UserServiceImpl.userService.requestMatchingDecision(
                //SharedPreferenceController.getUserToken(this), MatchingDecisionRequest(false)
                //objects.myToken, MatchingDecisionRequest(false)
                SharedPreferenceController.getUserToken(this),
                matchingDecisionRequest = MatchingDecisionRequest(false)
            )
            callMatchingDecision.safeEnqueue(onResponse = {
                if (it.isSuccessful) {
                    if (it.body()!!.status == 202) {
                        val choice = Intent(this, FailDialogActivity::class.java)
                        startActivityForResult(choice,4000)
                    }
                }
            })

        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 3000){
            finish()
        }
        if(requestCode == 4000){
            finish()
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