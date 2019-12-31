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
import android.widget.Toast
import com.project.mobo.R
import com.project.mobo.api.SignUpRequest
import com.project.mobo.api.SignUpResponse
import kotlinx.android.synthetic.main.activity_my_page_new.*
import kotlinx.android.synthetic.main.activity_sign_up_basic.*
import kotlinx.android.synthetic.main.activity_sign_up_basic.profile_image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_basic)


        //login()
        initialUI()
        picture()
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
            startActivity(login)
        }
        btnBack.setOnClickListener {
            finish()
        }
    }


//    private fun login() {
//        btnSignupNext?.setOnClickListener {
//            val id = edtSignupID?.text.toString()
//            val password = edtSignupPW?.text.toString()
//
//            if (id.isEmpty() || password.isEmpty()) {
//                // 사용자에게 간단한 text 정보를 알려주기 위해 Toast를 띄워준다.
//                Toast.makeText(this, "아이디나 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            Log.e(this::class.java.name, "실패1")
//
//            val call: Call<SignUpResponse> = `class RequestManager`.userService.requestSignUp(
//                "application/json",
//                SignUpRequest(id, password)
//            )
//
//            Log.e(this::class.java.name, "실패2")
//            call.enqueue(
//                object : Callback<SignUpResponse> {
//                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
//                        Log.e(this::class.java.name, "network error : $t")
//                    }
//
//                    override fun onResponse(
//                        call: Call<SignUpResponse>,
//                        response: Response<SignUpResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val signInResponse = response.body()!!
//
//                            Toast.makeText(
//                                this@SignUpBasicActivity,
//                                "$signInResponse",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        } else {
//                            Toast.makeText(
//                                this@SignUpBasicActivity,
//                                "Login Failed",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//                }
//            )
//        }
//
//    }

}
