package com.project.mobo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChattingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val ctnRvMyItem: View = view.findViewById(R.id.ctnRvMyItem)
    //val ctnRvYourItem: View = view.findViewById(R.id.ctnRvYourItem)
    val txtmyname: TextView = view.findViewById(R.id.tv_mychat_name)
    val txtmychat: TextView = view.findViewById(R.id.tv_mychat)


    fun bind(data: ChattingData) {
        txtmyname.text = data.name
        txtmychat.text = data.chat
    }
}