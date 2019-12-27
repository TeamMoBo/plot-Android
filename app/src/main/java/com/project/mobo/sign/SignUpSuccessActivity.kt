package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.R
import com.project.mobo.main_page.MainPageActivity
import kotlinx.android.synthetic.main.activity_sign_up_basic.*

class SignUpSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_success)

        initialUI()
    }

    private fun initialUI(){
        btnSignupNext.setOnClickListener {
            val login = Intent(this, SignInActivity::class.java)
            startActivity(login)
        }
    }
}
