package com.project.mobo.main_page.choice_time.innerData

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChoiceDateViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val ItemTime: TextView =view.findViewById(R.id.tvMainItemTime)

    fun bind(data: String){
        ItemTime.text=data
    }
}