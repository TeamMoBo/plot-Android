package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_matching_detailed.*

class MatchingDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_detailed)

        btnMatchingDetailedBack.setOnClickListener(){
            finish()
        }

//        val callMain = UserServiceImpl.detailService.matchingDetailResponse(
//            key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjM3LCJpYXQiOjE1Nzc3NTk2MzIsImV4cCI6MTU3ODM2NDQzMiwiaXNzIjoibW9ib21hc3RlciJ9.k30fv2OoezYTrzMJnhaFdM0suMnnoIVfjoGkOBMe-G4",
//
//            //key = SharedPreferenceController.getUserToken(this)
//        )


//        callMain.safeEnqueue (onResponse = {
//            if(it.isSuccessful){
//                mainData=it.body()!!.data
//                topThree() // 뷰페이저
//                choiceMovie() // 선택한 영화 창
//                choiceDate() // 선택한 시간 창
//            }
//
//        }, onError = {})
    }
}
