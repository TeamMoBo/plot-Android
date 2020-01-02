package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.project.mobo.R
import com.project.mobo.api.SignupRequest
import com.project.mobo.api.UserService
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnBack
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnSignupNext
import kotlinx.android.synthetic.main.activity_sign_up_plus.*

class SignUpPlusActivity : AppCompatActivity() {
    val networkService : UserService by lazy {
        UserServiceImpl.userService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_plus)

        val nickname: String = intent.getStringExtra("nickname")
        val name: String = intent.getStringExtra("name")
        val age: Int = intent.getIntExtra("age", 0)
        val id: String = intent.getStringExtra("id")
        val password: String = intent.getStringExtra("password")
        val school: String = intent.getStringExtra("school")
        val major: String = intent.getStringExtra("major")
        val kakaoID: String = intent.getStringExtra("kakaoID")
        val location: String = intent.getStringExtra("location")
        val gender: Int = intent.getIntExtra("myGender", 1)

        Log.d("test", nickname)

        //signUpPlus에서 받아오는 정보

        var selectGender: Int = 1

        rg_gender_plus.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButtonPlus =
                group?.findViewById(group.checkedRadioButtonId) as RadioButton
            checkedRadioButtonPlus.let {
                if (checkedRadioButtonPlus.isSelected) {
                    selectGender = checkedRadioButtonPlus.tag as Int
                    Log.v("gender", selectGender.toString())
                }
            }
        }


        val minAge = (txtMinAge.text.toString()).toInt()
        val maxAge = (txtMaxAge.text.toString()).toInt()
        //initialUI()

        btnSignupNext_plus.setOnClickListener {
            Log.d("test", "Click")
            val genre: String = edtSignupGenre.text.toString()
            val point: String = edtSignupPoint.text.toString()
            val interest: String = edtSignupInterest.text.toString()
            val comment: String = edtSignupSay.text.toString()
            val callSignUp = UserServiceImpl.signUpService.requestSignUp(
                SignupRequest(
                    id,
                    password,
                    name,
                    nickname,
                    age,
                    comment,
                    location,
                    gender,
                    selectGender,
                    minAge,
                    maxAge,
                    genre,
                    point,
                    interest,
                    school,
                    major,
                    kakaoID
                )
            )

            callSignUp.safeEnqueue {
                if(it.isSuccessful){
                    Log.d("test", "success")
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    val login = Intent(this, SignUpSuccessActivity::class.java)
                    startActivity(login)
                }
            }

        }
        btnBack.setOnClickListener {
            finish()
        }
    }
}
