package com.project.mobo.main_page.choice_time

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.main_page.choice_time.data.MainReserveDate

class DataVerticalAdapter(private val context: Context, val dateList : ArrayList<MainReserveDate>) : RecyclerView.Adapter<DataVerticalViewHolder>() {
    var data = listOf<DataVerticalItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVerticalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.date_choice_item, parent, false)

        return DataVerticalViewHolder(view)
    }

    override fun getItemCount(): Int = dateList.size

    override fun onBindViewHolder(holder: DataVerticalViewHolder, position: Int) {
        holder.bind(context, dateList[position].reservationDate, dateList[position].reservationTime)

    }
}