package com.setname.pusher.mvp.views.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.setname.pusher.R
import com.setname.pusher.mvp.presenter.MainPresenter
import com.setname.pusher.mvp.room.models.MessagesDatabaseModel
import com.setname.pusher.mvp.utils.receivers.SentMessageReceiver
import com.setname.pusher.mvp.views.fragments.main_container.FragmentCreateMessage
import com.setname.pusher.mvp.views.fragments.main_container.FragmentMessagesList
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), InteractionsWithMainView{

    private lateinit var presenter: MainPresenter

    override fun setFragmentMessages(list: List<MessagesDatabaseModel>) {

        val bundle = Bundle()
        bundle.putParcelable("list", ParcelableMessagesList(list))
        val fragment = FragmentMessagesList()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()

    }

    override fun startSentMessageReceiver(model: MessagesDatabaseModel) {

        val ALARM_CODE = resources.getInteger(R.integer.ALARM_CODE)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND))

        var intent = Intent(this, SentMessageReceiver::class.java)
        intent.putExtra("model", model)

        val pendingIntent = PendingIntent.getBroadcast(this, ALARM_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        Log.i("MainActivityLog", "${intent.getParcelableExtra<MessagesDatabaseModel>("model")}")

    }

    private var ALARM_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ALARM_CODE = resources.getInteger(R.integer.ALARM_CODE)

        presenter = MainPresenter(this)

    }

}

interface InteractionsWithMainView {

    fun startSentMessageReceiver(model: MessagesDatabaseModel)
    fun setFragmentMessages(list: List<MessagesDatabaseModel>)

}

@Parcelize
data class ParcelableMessagesList(val list: List<MessagesDatabaseModel>) : Parcelable
