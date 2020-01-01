package com.project.mobo

import android.app.Dialog
import android.content.Context

class CustomDialog : Dialog {
    constructor(context: Context?):super(context!!)

    override fun setContentView(layoutResID:Int) {
        super.setContentView(layoutResID)
        setContentView(R.layout.popup_matching_choice)
    }
}