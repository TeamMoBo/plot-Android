package com.project.mobo.main_page.choice_time

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.main_page.choice_time.innerData.ChoiceDateAdapter

class DataVerticalViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val ItemDay: TextView =view.findViewById(R.id.tvMainDay)
    val ItemDay2: TextView =view.findViewById(R.id.tvMainDayNum)
    val ItemRecyclerview: RecyclerView =view.findViewById(R.id.rvMainDay)

    fun bind(context : Context, data: String, dateList : ArrayList<String>){
        ItemDay.text=data
        ItemDay2.text=data

        ItemRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        ItemRecyclerview.adapter = ChoiceDateAdapter(context, dateList)
    }
}