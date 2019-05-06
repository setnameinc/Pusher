package com.setname.pusher.mvp.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import kotlinx.android.synthetic.main.adapter_model_messages.view.*
import java.util.*
import android.text.format.DateFormat


class MessagesAdapter(private val list: List<MessagesDatabaseModel>) :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_model_messages,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.setText(list[pos].main.message)
        holder.setDate(list[pos].time)
        holder.setImage(list[pos].posted)
    }

    inner class ViewHolder(val localeView: View) : RecyclerView.ViewHolder(localeView) {

        fun setText(str: String) {

            localeView.adapter_model_messages_text.text = str

        }

        fun setDate(time: Long) {

            localeView.adapter_model_messages_date.text = "${DateFormat.format("d MMMM", Date(time))}"

        }

        fun setImage(isDelivered:Boolean){

            var imageId:Int = 0

            if (isDelivered){

                imageId = R.mipmap.ic_delivered

            } else {

                imageId = R.mipmap.ic_on_await

            }

            localeView.adapter_model_messages_image_status.apply {

                background = ContextCompat.getDrawable(context, imageId)

            }

        }

    }


}