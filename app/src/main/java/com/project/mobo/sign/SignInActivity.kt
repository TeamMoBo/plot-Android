package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mobo.MainActivity
import com.project.mobo.R
import com.project.mobo.main_page.MainPageActivity
import com.project.mobo.mypage.MyPage_new
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initialUI()
    }

    private fun initialUI(){
        btnSigninLogin.setOnClickListener {
            val login = Intent(this, MainPageActivity::class.java)
            startActivity(login)
        }
        tvSigninSignup.setOnClickListener {
            val signUp = Intent(this, SignUpBasicActivity::class.java)
            startActivity(signUp)
        }


    }
}
