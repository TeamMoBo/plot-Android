package com.project.mobo.MatchingHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mobo.R
import com.project.mobo.api.HistoryData
import com.project.mobo.api.HistoryResponse
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_matching_detailed.*
import kotlinx.android.synthetic.main.activity_matching_history.*

class MatchingHistoryActivity : AppCompatActivity() {
    lateinit var matchingHistoryViewAdapter: MatchingHistoryViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_history)


        val callHistpry = UserServiceImpl.historyService.historyResponse(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjQwLCJpYXQiOjE1Nzc5NDkzNDYsImV4cCI6MTU3ODU1NDE0NiwiaXNzIjoibW9ib21hc3RlciJ9.dwKFFXHdDhkb8WW25BSMyig5DFzUlKPQ-WE1lzO4JBc"
            //key = SharedPreferenceController.getUserToken(this)
        )

        var historyData: ArrayList<HistoryData> = arrayListOf()

        callHistpry.safeEnqueue {
            if(it.isSuccessful){
                Log.d("test", "서버 연결 성공")

                historyData = it.body()!!.data
                matchingHistoryViewAdapter = MatchingHistoryViewAdapter(this, historyData)
                rvbtnMatchDetailedList.adapter = matchingHistoryViewAdapter
                rvbtnMatchDetailedList.layoutManager = LinearLayoutManager(this)
                (rvbtnMatchDetailedList.adapter as MatchingHistoryViewAdapter).notifyDataSetChanged()
            }
        }

        //왜 안되냠.... 서버에서 받는게 없을 때..나타나게 해야됨..
        /*if(historyData.size == 0) {
            tvMatchDetail1.setVisibility(View.VISIBLE)
            imgMatchDetailed1.setVisibility(View.VISIBLE)
        }
        else if(historyData.size>0) {
            tvMatchDetail1.setVisibility(View.INVISIBLE)
            imgMatchDetailed1.setVisibility(View.INVISIBLE)
        }*/

        btnMatchDetailedBack.setOnClickListener(){
            finish()
        }
        //setRecyclerView()
    }
    private fun setRecyclerView(){
        //임시 데이터
        var dataList : ArrayList<MatchingHistoryData> = ArrayList()
        dataList.add(MatchingHistoryData("2019-12-30", "겨울왕국",
            "https://mblogthumb-phinf.pstatic.net/MjAxOTAyMDhfNDkg/MDAxNTQ5NjE5NzA4NjMy.XkBEsFJf18l0f2_k9HmaSJZ-mUfMp-lmry89IzCUGgog.cW0B9s5fioVnbugS2t5YH7mVEKtksShQfcSkth45xy8g.JPEG.jjii-hhee0505/downloadfile-46.jpg?type=w800",
            "김민진", 23, "minjin"))
        dataList.add(MatchingHistoryData("2019-12-30", "겨울왕국",
            "https://mblogthumb-phinf.pstatic.net/MjAxOTAyMDhfNDkg/MDAxNTQ5NjE5NzA4NjMy.XkBEsFJf18l0f2_k9HmaSJZ-mUfMp-lmry89IzCUGgog.cW0B9s5fioVnbugS2t5YH7mVEKtksShQfcSkth45xy8g.JPEG.jjii-hhee0505/downloadfile-46.jpg?type=w800",
            "김민진", 23, "minjin"))
        dataList.add(MatchingHistoryData("2019-12-30", "겨울왕국",
            "https://mblogthumb-phinf.pstatic.net/MjAxOTAyMDhfNDkg/MDAxNTQ5NjE5NzA4NjMy.XkBEsFJf18l0f2_k9HmaSJZ-mUfMp-lmry89IzCUGgog.cW0B9s5fioVnbugS2t5YH7mVEKtksShQfcSkth45xy8g.JPEG.jjii-hhee0505/downloadfile-46.jpg?type=w800",
            "김민진", 23, "minjin"))

        //matchingHistoryViewAdapter = MatchingHistoryViewAdapter(this, dataList)
        //rvbtnMatchDetailedList.adapter = matchingHistoryViewAdapter
        //rvbtnMatchDetailedList.layoutManager = LinearLayoutManager(this)
    }
}
