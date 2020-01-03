package com.project.mobo.dialog

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.MatchingDetailedActivity
import com.project.mobo.MatchingHistory.MatchingHistoryActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import kotlinx.android.synthetic.main.popup_success_final.*

class SuccessDialogActivity : AppCompatActivity() {

    private lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_success_final)

        userName = SharedPreferenceController.getUserName(this)
        txtPopupFinalPerson.text = userName

        btnSuccessFinalOk.setOnClickListener {
            val choice = Intent(this, MatchingHistoryActivity::class.java)
            startActivityForResult(choice, 3000)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 3000){
            finish()
        }
    }
}
