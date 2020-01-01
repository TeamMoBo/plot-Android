package com.project.mobo.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.mobo.R
import com.project.mobo.api.UserServiceImpl
import kotlinx.android.synthetic.main.activity_temp.*
import retrofit2.Response

class TempActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        btn_temp.setOnClickListener {
            Log.v("token", "asas")

//            val postSign = UserServiceImpl.userService.requestSignIn(
//                SignInRequest(edit_id_temp.text.toString(),
//                    edit_pwd_temp.text.toString()).toString()
//            )

        }

    }

    val error : (Throwable) -> Unit = {
        //log
        Log.v("token1", it.toString())
    }

    val success : (TempResponse) -> Unit = {
        //blabla
        Log.v("token", it.data.token)

    }

    val fail : (Response<TempResponse>) -> Unit = {
        //blabla
        Log.v("token", it.toString())

    }
}
