package com.project.mobo.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.R

class ChatRoomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<ChatData> = mutableListOf()

    fun addItem(chatData: ChatData) {
        data.add(chatData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var viewHolder: RecyclerView.ViewHolder

        return when (viewType) {
            MY_CHAT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_mychat_item, parent, false)

                ChatMyViewHolder(view)
            }
            OTHER_CHAT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_yourchat_item, parent, false)

                ChatOtherViewHolder(view)
            }
            else -> {
                viewHolder
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatMyViewHolder -> {
                holder.onBind(data[position])
            }
            is ChatOtherViewHolder -> {
                holder.onBind(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].uid == "jihye")
            MY_CHAT
        else OTHER_CHAT
    }

    companion object {
        const val MY_CHAT = 0 // 내 채팅
        const val OTHER_CHAT = 1 // 다른 사람 채팅
    }
}