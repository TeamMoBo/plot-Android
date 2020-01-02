package com.project.mobo.dialog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.MatchingDetailedActivity
import com.project.mobo.R
import kotlinx.android.synthetic.main.popup_success_final.*

class SuccessDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_success_final)
        btnSuccessFinalOk.setOnClickListener{
            val choice = Intent(this, MatchingDetailedActivity::class.java)
            startActivity(choice)
            finish()
        }
    }
}
