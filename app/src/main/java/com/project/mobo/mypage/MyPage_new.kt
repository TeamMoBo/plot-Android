package com.project.mobo.mypage

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build.*
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mobo.PayChoiceActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.api.*
import kotlinx.android.synthetic.main.activity_my_page_new.*

class MyPage_new : AppCompatActivity() {
    //
    lateinit var myTicketData: HaveTicketData

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_new)

        val callMypage = UserServiceImpl.myPageService.myPageRead(
            SharedPreferenceController.getUserToken(this)
        )

        var mypageInfo: MyPageDataD
        callMypage.safeEnqueue {
            if (it.isSuccessful) {
                mypageInfo = it.body()!!.data
                profiletxt.setText(mypageInfo.userComment)
                profile_name.setText(mypageInfo.userNickname)
                edittxt_age.setText(mypageInfo.userAge.toString())
                edittxt_school.setText(mypageInfo.userSchool)
                edittxt_department.setText(mypageInfo.userMajor)
                edittxt_livingarea.setText(mypageInfo.userLocation)
                edittxt_name.setText(mypageInfo.userName)
                edittxt_id.setText(mypageInfo.userId)
                when (mypageInfo.userSelectGender) {
                    0 -> rg_btn2_mypage.isChecked = true
                    1 -> rg_btn1_mypage.isChecked = true
                    2 -> rg_btn3_mypage.isChecked = true
                }
                edittxt_ageMin.setText(mypageInfo.userSelectMinAge.toString())
                edittxt_ageMax.setText(mypageInfo.userSelectMaxAge.toString())
                edittxt_kakaotalk.setText(mypageInfo.userKakao)
            }
        }

        val callMyHaveServiece = UserServiceImpl.haveTicketService.responseHaveTicket(
            key = SharedPreferenceController.getUserToken(this)
        )

        callMyHaveServiece.safeEnqueue {
            if(it.isSuccessful){
                myTicketData = it.body()!!.data
                txt_ticket.text = myTicketData.userTicket.toString()
                txt_popcorn.text = myTicketData.userPopcorn.toString()
            }
        }


        //Change profile Image
        profile_image.setOnClickListener {
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
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

        //Back button
        backBtn.setOnClickListener {
            finish()
        }

        btn_goTicket.setOnClickListener {
            val intent = Intent(this, PayChoiceActivity::class.java)
            startActivity(intent)
        }

        changetxteBtn.setOnClickListener {
            editText(profiletxt)
        }

        changeNameBtn.setOnClickListener {
            editText(profile_name)
        }

        changeBtn_introOneLine.setOnClickListener {
            editText(profile_introOneLine)
        }

        val listEditText: ArrayList<EditText> = arrayListOf<EditText>(
            edittxt_age, edittxt_school,
            edittxt_department, edittxt_livingarea, edittxt_name, edittxt_id,
            edittxt_kakaotalk, edittxt_ageMin, edittxt_ageMax
        )

        //val listRadioButton: ArrayList<RadioButton> = arrayListOf(rg_btn1, rg_btn2, rg_btn3)
        val listRadioButton: ArrayList<RadioButton> =
            arrayListOf(rg_btn1_mypage, rg_btn2_mypage, rg_btn3_mypage)

        btn_change_info.setOnClickListener {
            btn_change_info.visibility = EditText.INVISIBLE
            btn_change_complete.visibility = EditText.VISIBLE
            for (i in listEditText) {
                editText(i)
            }

            for (i in listRadioButton) {
                editRadio(i)
            }


        }

        btn_change_complete.setOnClickListener {
            btn_change_info.visibility = EditText.VISIBLE
            btn_change_complete.visibility = EditText.INVISIBLE
            for (i in listEditText) {
                editText(i)
            }

            for (i in listRadioButton) {
                editRadio(i)
            }
        }
    }

    private fun editText(eText: EditText) {
        if (eText.isFocusableInTouchMode) {
            eText.isFocusableInTouchMode = false
            eText.isEnabled = false
            if (eText.text.toString() == "") {
                eText.setText(eText.hint.toString())
            }
        } else if (!eText.isFocusableInTouchMode) {
            eText.isEnabled = true
            eText.isFocusableInTouchMode = true
            eText.hint = eText.text
            eText.setText("")
        }
    }

    private fun editRadio(eText: RadioButton) {
        if (eText.isFocusableInTouchMode) {
            eText.isFocusableInTouchMode = false
            eText.isEnabled = false
        } else if (!eText.isFocusableInTouchMode) {
            eText.isEnabled = true
            eText.isFocusableInTouchMode = true
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
}