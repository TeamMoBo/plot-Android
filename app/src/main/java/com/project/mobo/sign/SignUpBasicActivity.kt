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

            val photo = profile_image.imageView
            val nickname = edtSignupNickname.text.toString()
            val name = edtSignupName.text.toString()
            val age = edtSignupAge.text.toString()
            val id = edtSignupID.text.toString()
            val password = edtSignupPW.text.toString()
            val school = edtSignupUniv.text.toString()
            val major = edtSignupMajor.text.toString()
            val kakaoID = edtSignupKakao.text.toString()
            val location = edtSignupLocation.text.toString()

            var gender: Int = 1

            rg_gender_basic.setOnCheckedChangeListener { group, checkedId ->
                val checkedRadioButton =
                    group?.findViewById(group.checkedRadioButtonId) as RadioButton
                checkedRadioButton?.let {
                    if (checkedRadioButton.isChecked) {
                        gender = checkedRadioButton.tag as Int
                    }
                }
            }

            if (nickname.isEmpty() || name.isEmpty() || age.isEmpty() || id.isEmpty() || password.isEmpty()
                || school.isEmpty() || major.isEmpty() || kakaoID.isEmpty() || location.isEmpty()
            ) {
                Toast.makeText(this, "빈칸을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                intent.putExtra("nickname", nickname)
                intent.putExtra("name", name)
                intent.putExtra("age", age)
                intent.putExtra("id", id)
                intent.putExtra("password", password)
                intent.putExtra("school", school)
                intent.putExtra("major", major)
                intent.putExtra("kakaoID", kakaoID)
                intent.putExtra("location", location)
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