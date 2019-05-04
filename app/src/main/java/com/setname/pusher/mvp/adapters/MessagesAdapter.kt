package com.setname.pusher.mvp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import kotlinx.android.synthetic.main.adapter_messages.view.*

class MessagesAdapter(private val list: List<MessagesDatabaseModel>) :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_messages,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.setText(list[pos].main.message)
    }

    inner class ViewHolder(val localeView: View) : RecyclerView.ViewHolder(localeView) {

        fun setText(str: String) {

            localeView.adapter_messages_text.text = str

        }

    }


}