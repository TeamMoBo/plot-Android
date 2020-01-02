package com.project.mobo.main_page.choice_time

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R
import com.project.mobo.main_page.data.MainReserveDate
import com.project.mobo.temp.ReserveDate

class DataVerticalAdapter(private val context: Context, private var reserveDate : ArrayList<ReserveDate>) : RecyclerView.Adapter<DataVerticalViewHolder>() {
    //var data = listOf<DataVerticalItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVerticalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.date_choice_item, parent, false)

        return DataVerticalViewHolder(view)
    }

    override fun getItemCount(): Int = reserveDate.size

    override fun onBindViewHolder(holder: DataVerticalViewHolder, position: Int) {
        //holder.bind(context, dateList[position].reservationDate, dateList[position].reservationTime)
        holder.bind(context, reserveDate[position].reservationDate, reserveDate[position].reservationTime)

    }
}