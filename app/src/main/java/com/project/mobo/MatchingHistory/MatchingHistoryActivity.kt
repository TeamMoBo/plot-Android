package com.project.mobo.MatchingHistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.MatchingDetailedActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.api.HistoryData
import com.project.mobo.api.HistoryResponse
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_matching_detailed.*
import kotlinx.android.synthetic.main.activity_matching_history.*

class MatchingHistoryActivity : AppCompatActivity(), View.OnClickListener {
    var matchingHistoryViewAdapter: MatchingHistoryViewAdapter = MatchingHistoryViewAdapter(this)
    private lateinit var rv: RecyclerView
    var matchingData: ArrayList<HistoryData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_history)

        rv = findViewById(R.id.rvbtnMatchDetailedList)
        matchingHistoryViewAdapter.notifyDataSetChanged()

        val callHistpry = UserServiceImpl.historyService.historyResponse(
            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjQwLCJpYXQiOjE1Nzc5NDkzNDYsImV4cCI6MTU3ODU1NDE0NiwiaXNzIjoibW9ib21hc3RlciJ9.dwKFFXHdDhkb8WW25BSMyig5DFzUlKPQ-WE1lzO4JBc"
            //key = SharedPreferenceController.getUserToken(this)
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjEwNiwiaWF0IjoxNTc3OTYyMjI4LCJleHAiOjE1Nzg1NjcwMjgsImlzcyI6Im1vYm9tYXN0ZXIifQ.0WjcIhqwjRVc-B_DxLbbyRz_OgxR-L-r6W1J1kMj8CI"
        )

        callHistpry.safeEnqueue {
            if(it.isSuccessful){
                Log.d("test", "서버 연결 성공 1")

                matchingHistoryViewAdapter.historydata = it.body()!!.data
                matchingData = it.body()!!.data

                rv.layoutManager = LinearLayoutManager(this)
                matchingHistoryViewAdapter.notifyDataSetChanged()
                matchingHistoryViewAdapter.onItemClick(this)

                rv.adapter = matchingHistoryViewAdapter

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

        btnMatchDetailedBack.setOnClickListener{
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

    override fun onClick(v: View?) {
        Log.d("test","click")
        if (v?.parent == rv){
            val intent: Intent = Intent(this, MatchingDetailedActivity::class.java)
            intent.putExtra("matchingIdx", matchingData[rv.getChildAdapterPosition(v)].matchingIdx)
            startActivity(intent)
        }
        //Log.d("test",idx.toString())
    }
}
