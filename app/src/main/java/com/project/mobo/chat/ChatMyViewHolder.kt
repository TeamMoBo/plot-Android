package com.project.mobo.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChatMyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    //val name: TextView = view.findViewById(R.id.txtMyname)
    val msg: TextView = view.findViewById(R.id.txtMymessage)
    val time: TextView = view.findViewById(R.id.txtCurrentTime)

    fun onBind(data: ChatData) {
        //name.text = data.uid
        msg.text = data.message
       // time.text=
    }
}