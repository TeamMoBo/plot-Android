package com.project.mobo.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.MainPageActivity
import com.project.mobo.R
import kotlinx.android.synthetic.main.activity_sign_up_basic.*

class SignUpSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_success)

        initialUI()
    }

    private fun initialUI(){
        btnSignupNext.setOnClickListener {
            val login = Intent(this, MainPageActivity::class.java)
            startActivity(login)
        }
    }
}
