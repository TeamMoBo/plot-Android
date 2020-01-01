package com.project.mobo.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.project.mobo.MatchingWaitingActivity
import com.project.mobo.R
import kotlinx.android.synthetic.main.success_dialog.*

class SuccessDialog : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.success_dialog, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        btn_success.setOnClickListener {
            startActivity(Intent(context, MatchingWaitingActivity::class.java))
            dismiss()
        }
    }


}