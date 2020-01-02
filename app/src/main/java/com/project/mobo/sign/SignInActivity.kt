package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.api.SigninRequest
import com.project.mobo.api.Token
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.main_page.MainPageActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {


    private lateinit var loginData: Token

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initialUI()
        //login()
    }

    private fun initialUI() {
        btnSigninLogin.setOnClickListener {
            val id = edtSigninID?.text.toString()
            val password = edtSigninPW?.text.toString()

            if (id.isEmpty() || password.isEmpty()) {
                // 사용자에게 간단한 text 정보를 알려주기 위해 Toast를 띄워준다.
                Toast.makeText(this, "아이디나 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val callLogin = UserServiceImpl.userService.requestSignIn(SigninRequest(id, password))

            callLogin.safeEnqueue(onResponse = {
                if(it.isSuccessful){
                    loginData=it.body()!!.data
                    SharedPreferenceController.setUserToken(this, loginData.token)
                    Log.v("token", loginData.token)
                    val login = Intent(this, MainPageActivity::class.java)
                    startActivity(login)
                }
            })

        }
        tvSigninSignup.setOnClickListener {
            val signUp = Intent(this, SignUpBasicActivity::class.java)
            startActivity(signUp)
        }


    }

//    private fun login(){
//        btnSigninLogin?.setOnClickListener {
//            val id = edtSigninID?.text.toString()
//            val password = edtSigninPW?.text.toString()
//
//            if (id.isEmpty() || password.isEmpty()) {
//                // 사용자에게 간단한 text 정보를 알려주기 위해 Toast를 띄워준다.
//                Toast.makeText(this, "아이디나 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            Log.e(this::class.java.name, "실패1")
//
//            val call: Call<SignInResponse> = UserServiceImpl.userService.requestSignIn(SignInRequest(id, password).toString())
//
//            Log.e(this::class.java.name, "실패2")
//            call.enqueue(
//                object : Callback<SignInResponse> {
//                    override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
//                        Log.e(this::class.java.name, "network error : $t")
//                    }
//
//                    override fun onResponse(
//                        call: Call<SignInResponse>,
//                        response: Response<SignInResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val signInResponse = response.body()!!
//
//                            Toast.makeText(this@SignInActivity, "$signInResponse", Toast.LENGTH_LONG).show()
//                        }
//                        else {
//                            Toast.makeText(this@SignInActivity, "Login Failed", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//            )
//        }
//
//    }
}
