package com.project.mobo.sign

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.project.mobo.R
import com.project.mobo.api.UserServiceImpl
import kotlinx.android.synthetic.main.activity_my_page_new.*
import kotlinx.android.synthetic.main.activity_sign_in.view.*
import kotlinx.android.synthetic.main.activity_sign_up_basic.*
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnBack
import kotlinx.android.synthetic.main.activity_sign_up_basic.btnSignupNext
import kotlinx.android.synthetic.main.activity_sign_up_basic.profile_image

class SignUpBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_basic)

        //login()
        initialUI()
        picture()
        condition()

    }

    private fun picture() {
        //Change profile Image
        profile_image.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {

                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            }
        }
    }

    //Change profile image
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            profile_image.setImageURI(data?.data)
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) run {
                val newName: String? = data?.getStringExtra("data")
                profile_name.setText(newName)
            }
        }
    }


    private fun initialUI() {
        btnSignupNext.setOnClickListener {
            val login = Intent(this, SignUpPlusActivity::class.java)

            //일단 넣지 않을 것.

           // val photo = "data:image/jpeg;base64,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"
//            val nickname = edtSignupNickname.text.toString()
//            val name = edtSignupName.text.toString()
//            val age = edtSignupAge.text.toString()
//            val id = edtSignupID.text.toString()
//            val password = edtSignupPW.text.toString()
//            val school = edtSignupUniv.text.toString()
//            val major = edtSignupMajor.text.toString()
//            val kakaoID = edtSignupKakao.text.toString()
//            val location = edtSignupLocation.text.toString()

            val nickname = "kim"
            val name = "kim"
            val age = 3
            val id = "kim"
            val password = "kim"
            val school = "kim"
            val major = "kim"
            val kakaoID = "kim"
            val location = "kim"

            var gender = 1

//            rg_gender_basic.setOnCheckedChangeListener { group, checkedId ->
//                val checkedRadioButton =
//                    group?.findViewById(group.checkedRadioButtonId) as RadioButton
//                checkedRadioButton?.let {
//                    if (checkedRadioButton.isChecked) {
//                        gender = checkedRadioButton.tag as Int
//                        Log.v("my_gender", gender.toString())
//                    }
//                }
//            }

            Log.v("my_gender", gender.toString())

            if(gender.toString().isEmpty()){
                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
            }


            if (nickname.isEmpty() || name.isEmpty() || id.isEmpty() || password.isEmpty()
                || school.isEmpty() || major.isEmpty() || kakaoID.isEmpty() || location.isEmpty()
            ) {
                Toast.makeText(this, "빈칸을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                //intent.putExtra("profile", photo)
                intent.putExtra("nickname", nickname)
                intent.putExtra("name", name)
                intent.putExtra("age", age)
                intent.putExtra("id", id)
                intent.putExtra("password", password)
                intent.putExtra("school", school)
                intent.putExtra("major", major)
                intent.putExtra("kakaoID", kakaoID)
                intent.putExtra("location", location)
                intent.putExtra("myGender", gender)
                setResult(Activity.RESULT_OK, intent) // StartActivityForResult

                startActivity(login)
            }
        }
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun condition(){
        edtSignupID.text.toString()
    }

}