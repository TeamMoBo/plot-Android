package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mobo.chat.ChattingActivity
import com.project.mobo.main_page.MainPageActivity
import kotlinx.android.synthetic.main.activity_matching_detailed.*

class MatchingDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_detailed)

        btnMatchingDetailedBack.setOnClickListener {
            val main = Intent(this, MainPageActivity::class.java)
            startActivity(main)
            finish()
        }
    }
}
