package com.project.mobo.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//        val genre = edtSignupGenre.text.toString()
//        val point = edtSignupPoint.text.toString()
//        val interest = edtSignupInterest.text.toString()
//        val comment = edtSignupSay.text.toString()

        val genre = "kim"
        val point = "kim"
        val interest = "kim"
        val comment = "kim"

        var selectGender: Int = 1

//        rg_gender_plus.setOnCheckedChangeListener { group, checkedId ->
//            val checkedRadioButtonPlus =
//                group?.findViewById(group.checkedRadioButtonId) as RadioButton
//            checkedRadioButtonPlus?.let {
//                if (checkedRadioButtonPlus.isSelected) {
//                    selectGender = checkedRadioButtonPlus.tag as Int
//                    Log.v("gender", selectGender.toString())
//                }
//            }
//        }


//        val minAge = (txtMinAge.text.toString()).toInt()
//        val maxAge = (txtMaxAge.text.toString()).toInt()

        val minAge = 23
        val maxAge = 23
        //initialUI()

        btnSignupNext.setOnClickListener {
            val callSignUp = UserServiceImpl.userService.requestSignUp(
                SignupRequest(
                    "id",
                    "password",
                    "name",
                    "nickname",
                    age,
                    "comment",
                    "location",
                    gender,
                    selectGender,
                    minAge,
                    maxAge,
                    genre,
                    "point",
                    "interest",
                    "school",
                    "major",
                    "kakaoID"
                )
            )

            callSignUp.safeEnqueue(onResponse = {
                if(it.isSuccessful) {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    val login = Intent(this, SignUpSuccessActivity::class.java)
                    startActivity(login)
                }
            }, onError = {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            })


        }
        btnBack.setOnClickListener {
            finish()
        }
    }

//    private fun requestSignUp(){
//
//        var jsonObject = JSONObject()
//
//        jsonObject.put("id", id)
//        jsonObject.put("password", password)
//        jsonObject.put("name", name)
//        jsonObject.put("nickname", nickname)
//        jsonObject.put("age", age)
//        jsonObject.put("comment", comment)
//        jsonObject.put("location", location)
//        jsonObject.put("gender", gender)
//        jsonObject.put("selectGender", selectGender)
//        jsonObject.put("selectMinAge", minAge)
//        jsonObject.put("selectMaxAge", maxAge)
//        jsonObject.put("preferGenre", genre)
//        jsonObject.put("attractPoint", point)
//        jsonObject.put("favor", interest)
//        jsonObject.put("school", school)
//        jsonObject.put("major", major)
//        jsonObject.put("kakao", kakaoID)
//
//        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
//        val postSignupResponse = networkService.requestSignUp("application/json", jsonObject)
//    }

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
