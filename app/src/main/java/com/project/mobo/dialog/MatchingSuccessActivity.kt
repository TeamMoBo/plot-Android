package com.project.mobo.dialog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mobo.MatchingWaitingActivity
import com.project.mobo.R
import kotlinx.android.synthetic.main.popup_matching_success.*

class MatchingSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_matching_success)

        btnSuccessOk.setOnClickListener {
            val choice = Intent(this, MatchingWaitingActivity::class.java)
            startActivity(choice)
            finish()
        }
    }
}