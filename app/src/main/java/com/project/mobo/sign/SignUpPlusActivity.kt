package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.project.mobo.R
import com.project.mobo.api.SignupRequest
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_sign_up_basic.*
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnBack
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnSignupNext
import kotlinx.android.synthetic.main.activity_sign_up_plus.*
import okhttp3.RequestBody
import java.io.File

class SignUpPlusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_plus)

        val profile = intent.getStringExtra("profile")
        val nickname = intent.getStringExtra("nickname")
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val id = intent.getStringExtra("id")
        val password = intent.getStringExtra("password")
        val school = intent.getStringExtra("school")
        val major = intent.getStringExtra("major")
        val kakaoID = intent.getStringExtra("kakaoID")
        val location = intent.getStringExtra("location")
        val gender = intent.getIntExtra("myGender", 1)

        //signUpPlus에서 받아오는 정보
        val genre = edtSignupGenre.text.toString()
        val point = edtSignupPoint.text.toString()
        val interest = edtSignupInterest.text.toString()
        val comment = edtSignupSay.text.toString()

        var selectGender: Int = 1

        rg_gender_plus.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton_plus =
                group?.findViewById(group.checkedRadioButtonId) as RadioButton
            checkedRadioButton_plus?.let {
                if (checkedRadioButton_plus.isChecked) {
                    selectGender = checkedRadioButton_plus.tag as Int
                }
            }
        }

        val minAge = (txtMinAge.text.toString()).toInt()
        val maxAge = (txtMaxAge.text.toString()).toInt()

        //initialUI()

        btnSignupNext.setOnClickListener {
            val callsingUp = UserServiceImpl.userService.requestSignUp(
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
                    kakaoID,
                    profile
                )
            )

            callsingUp.safeEnqueue(onResponse = {
                if(it.isSuccessful){
                    val login = Intent (this, SignUpSuccessActivity::class.java)
                    startActivity(login)
                }
            })


        }
        btnBack.setOnClickListener {
            finish()
        }
    }

//    private fun initialUI(){
//        btnSignupNext.setOnClickListener {
//            UserServiceImpl.userService.requestSignUp(SignupRequest(id,
//            val login = Intent(this, SignUpSuccessActivity::class.java)
//            startActivity(login)
//        }
//        btnBack.setOnClickListener {
//            finish()
//        }
//    }
}
