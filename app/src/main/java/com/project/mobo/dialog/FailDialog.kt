package com.project.mobo.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.project.mobo.R
import kotlinx.android.synthetic.main.fail_dialog.*

class FailDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fail_dialog, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        btn_fail.setOnClickListener {
            dismiss()
        }
    }
}