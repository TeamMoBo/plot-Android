package com.project.mobo.MatchingHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mobo.R
import kotlinx.android.synthetic.main.activity_matching_detailed.*
import kotlinx.android.synthetic.main.activity_matching_history.*

class MatchingHistoryActivity : AppCompatActivity() {
    lateinit var matchingHistoryViewAdapter: MatchingHistoryViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_history)

        setRecyclerView()
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

        matchingHistoryViewAdapter = MatchingHistoryViewAdapter(this, dataList)
        rvbtnMatchDetailedList.adapter = matchingHistoryViewAdapter
        rvbtnMatchDetailedList.layoutManager = LinearLayoutManager(this)
    }
}
