package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initialUI()
    }

    private fun initialUI(){
        btnSigninLogin.setOnClickListener {
            val login = Intent(this, SignUpBasicActivity::class.java)
            startActivity(login)
        }
    }
}