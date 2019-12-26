package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.R
import kotlinx.android.synthetic.main.activity_sign_up_basic.*

class SignUpBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_basic)

        initialUI()
    }

    private fun initialUI(){
        btnSignupNext.setOnClickListener {
            val login = Intent(this, SignUpPlusActivity::class.java)
            startActivity(login)
        }
    }
}
