package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.chat.ChattingActivity
import com.project.mobo.main_page.MainPageActivity
import kotlinx.android.synthetic.main.activity_matching_detailed.*

class MatchingDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_detailed)
        val matchingIdx = intent.getIntExtra("matchingIdx", 1)
        Log.d("test",matchingIdx.toString())

        val callMatchingDetail = UserServiceImpl.matchingDetailService.matchingDetailResponse(
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjEwNiwiaWF0IjoxNTc3OTYyMjI4LCJleHAiOjE1Nzg1NjcwMjgsImlzcyI6Im1vYm9tYXN0ZXIifQ.0WjcIhqwjRVc-B_DxLbbyRz_OgxR-L-r6W1J1kMj8CI",
            matchingIdx = matchingIdx.toString()
        )

        callMatchingDetail.safeEnqueue {
            Log.d("test", "서버 통신")
            if(it.isSuccessful){
                val myData = it.body()!!.data
                txtMatchingDetailedName.setText(myData.name)
                txtMatchingDetailedMessage.setText(myData.comment)
                tag1.setText("#${myData.hashtTag[0]}")
                tag2.setText("#${myData.hashtTag[1]}")
                tag3.setText("#${myData.hashtTag[2]}")
                txtMatchingDetailedSchool.setText(myData.school)
                txtMatchingDetailedLocation.setText(myData.location)
                txtMatchingDetailedChatID.setText(myData.kakaotalk)
                txtMatchingDetailedMovieTitle.setText(myData.movieTitle)
            }
        }

        btnMatchingDetailedBack.setOnClickListener {
            finish()
        }
    }
}
