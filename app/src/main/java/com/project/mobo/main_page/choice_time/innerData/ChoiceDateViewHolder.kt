package com.project.mobo.main_page.choice_time.innerData

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChoiceDateViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val ItemTime: TextView =view.findViewById(R.id.tvMainItemTime)

    //var num : Int = Integer.parseInt(ItemTime)

    fun bind(data: Int){
        //ItemTime.text=data
        ItemTime.text=Integer.toString(data)
    }
}