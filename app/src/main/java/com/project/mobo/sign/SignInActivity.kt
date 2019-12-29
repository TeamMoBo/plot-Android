package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.project.mobo.R
import com.project.mobo.api.SignInRequest
import com.project.mobo.api.SignInResponse
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.main_page.MainPageActivity
import com.project.mobo.mypage.MyPage_new
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun login(){
        btnSigninLogin.setOnClickListener {
            val id = edtSigninID.text.toString()
            val password = edtSigninPW.text.toString()

            val call: Call<SignInResponse> = UserServiceImpl.userService.requestSignIn(SignInRequest(id, password))

            call.enqueue(
                object : Callback<SignInResponse> {
                    override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                        Log.e(this::class.java.name, "network error : $t")
                    }

                    override fun onResponse(
                        call: Call<SignInResponse>,
                        response: Response<SignInResponse>
                    ) {
                        if (response.isSuccessful) {
                            val signInResponse = response.body()!!

                            Toast.makeText(this@SignInActivity, "$signInResponse", Toast.LENGTH_LONG).show()
                        }
                        else {
                            Toast.makeText(this@SignInActivity, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )
        }

    }
}
