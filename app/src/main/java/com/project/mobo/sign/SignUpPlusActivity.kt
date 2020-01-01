package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.R
import kotlinx.android.synthetic.main.activity_sign_up_basic.*

class SignUpPlusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_plus)


        val nickname = intent.getStringExtra("nickname")
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val id = intent.getStringExtra("id")
        val password = intent.getStringExtra("password")
        val school = intent.getStringExtra("school")
        val major = intent.getStringExtra("major")
        val kakaoID = intent.getStringExtra("kakaoID")
        val location = intent.getStringExtra("location")

        initialUI()
    }

    private fun initialUI(){
        btnSignupNext.setOnClickListener {
            val login = Intent(this, SignUpSuccessActivity::class.java)
            startActivity(login)
        }
        btnBack.setOnClickListener {
            finish()
        }
    }
}
