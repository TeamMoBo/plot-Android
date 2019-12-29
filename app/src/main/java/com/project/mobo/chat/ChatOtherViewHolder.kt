package com.project.mobo.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChatOtherViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val name: TextView =view.findViewById(R.id.txtYourname)
    val msg:TextView=view.findViewById(R.id.txtYourmessage)

    fun onBind(data:ChatData){
        name.text=data.uid
        msg.text=data.message
    }

}