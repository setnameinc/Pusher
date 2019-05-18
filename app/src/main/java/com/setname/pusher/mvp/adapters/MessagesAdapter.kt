package com.setname.pusher.mvp.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.setname.pusher.R
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.context.AppContext
import kotlinx.android.synthetic.main.adapter_model_messages.view.*
import java.util.*

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

    override fun getItemViewType(position: Int): Int = if (list[position].posted) TypesOfMessages.PUSHED.typeNumber else TypesOfMessages.NOT_PUSHED.typeNumber

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {

        holder.setText(list[pos].main.message)
        holder.setDate(list[pos].time)
        holder.setImage(list[pos].posted)

    }

    inner class ViewHolder(private val localeView: View) : RecyclerView.ViewHolder(localeView), View.OnClickListener {

        init {

            localeView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            Log.i("MessageAdapter", "clicked")

            val funArray = arrayOf("Force push", "Show full data")

        }

        fun setText(str: String) {

            localeView.adapter_model_messages_text.text = str

        }

        fun setDate(time: Long) {

            val strFormat = "HH:mm"

            val calendarToday = Calendar.getInstance()
            calendarToday.time = Date()

            val calendarSometime = Calendar.getInstance()
            calendarSometime.time = Date(time)

            if (calendarSometime.get(Calendar.YEAR) == calendarToday.get(Calendar.YEAR)
                && calendarSometime.get(Calendar.DAY_OF_YEAR) == calendarToday.get(Calendar.DAY_OF_YEAR)
            ) {

                localeView.adapter_model_messages_date.text = "${DateFormat.format(strFormat, Date(time))}"

            } else {

                localeView.adapter_model_messages_date.text = "${DateFormat.format("d MMMM", Date(time))}"

            }

            setLongClickListenerDate(time)

        }

        private fun setLongClickListenerDate(time: Long) {

            localeView.adapter_model_messages_date.setOnLongClickListener {

                Toast.makeText(
                    localeView.context,
                    "${DateFormat.format("d MMMM HH:mm", Date(time))}",
                    Toast.LENGTH_SHORT
                )
                    .show()

                return@setOnLongClickListener false

            }

        }

        fun setImage(isDelivered: Boolean) {

            var imageId: Int = 0

            if (isDelivered) {

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

enum class TypesOfMessages(val typeNumber:Int){
    PUSHED(1), NOT_PUSHED(2)
}
